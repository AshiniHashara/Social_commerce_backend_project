package com.socialCommerce.backend_social.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppController {

    @PostMapping
    public ResponseEntity<Map<String, String>> generateWhatsAppLink(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        String encodedMessage = UriUtils.encodeQuery(message, StandardCharsets.UTF_8);
        String whatsappLink = "https://api.whatsapp.com/send?text=" + encodedMessage;

        Map<String, String> response = new HashMap<>();
        response.put("url", whatsappLink);

        return ResponseEntity.ok(response);
    }

}


