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
        if (repo.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public AuthResponse verify(User loginPayload) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginPayload.getUsername(), loginPayload.getPassword()
                )
        );

        if (!auth.isAuthenticated()) {
            throw new RuntimeException("Invalid login");
        }


        User user = repo.findByUsername(loginPayload.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found after auth");
        }


        String accessToken = service.generateAccessToken(user.getUsername(),user.getRole().name());
        String refreshToken = service.generateRefreshToken(user.getUsername());


        List<Token> oldTokens = tokenRepo.findAllValidTokensByUser(user.getId());
        if (!oldTokens.isEmpty()) {
            oldTokens.forEach(t -> t.setLoggedOut(true));
            tokenRepo.saveAll(oldTokens);
        }


        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepo.save(token);


        return new AuthResponse(accessToken, refreshToken ,user.getUsername(), user.getRole().name());
    }

}

