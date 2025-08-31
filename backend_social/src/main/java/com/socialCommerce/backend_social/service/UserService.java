package com.socialCommerce.backend_social.service;

import com.socialCommerce.backend_social.model.AuthResponse;
import com.socialCommerce.backend_social.model.Token;
import com.socialCommerce.backend_social.model.User;
//import com.socialCommerce.backend_social.repo.TokenRepo;
import com.socialCommerce.backend_social.repo.TokenRepo;
import com.socialCommerce.backend_social.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService service;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User register(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    //return service.generateAccessToken(user.getUsername());

//    public AuthResponse verify(User user) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//        );
//        if (authentication.isAuthenticated()) {
//            String accessToken = service.generateAccessToken(user.getUsername());
//            String refreshToken = service.generateRefreshToken(user.getUsername());
//
//            saveUserToken(accessToken, refreshToken, user);
//
//            return new AuthResponse(accessToken, refreshToken);
//        }
//        throw new RuntimeException("Invalid login");
//    }
//public AuthResponse verify(User user) {
//    Authentication authentication = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//    );
//
//    if (authentication.isAuthenticated()) {
//        String accessToken = service.generateAccessToken(user.getUsername());
//        String refreshToken = service.generateRefreshToken(user.getUsername());
//
//        // optionally save tokens in DB
//        //saveUserToken(accessToken, refreshToken, user);
//
//        return new AuthResponse(accessToken, refreshToken);
//    }
//
//    throw new RuntimeException("Invalid login credentials");
//}

    public AuthResponse verify(User loginPayload) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginPayload.getUsername(), loginPayload.getPassword()
                )
        );

        if (!auth.isAuthenticated()) {
            throw new RuntimeException("Invalid login");
        }

        // 1) Load the managed user from DB
        User user = repo.findByUsername(loginPayload.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found after auth");
        }

        // 2) Generate tokens
        String accessToken = service.generateAccessToken(user.getUsername());
        String refreshToken = service.generateRefreshToken(user.getUsername());

        // 3) Revoke old tokens for this user (optional but recommended)
        List<Token> oldTokens = tokenRepo.findAllValidTokensByUser(user.getId());
        if (!oldTokens.isEmpty()) {
            oldTokens.forEach(t -> t.setLoggedOut(true));
            tokenRepo.saveAll(oldTokens);
        }

        // 4) Save the new token row
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepo.save(token);

        // 5) Return both tokens to frontend
        return new AuthResponse(accessToken, refreshToken);
    }

}

