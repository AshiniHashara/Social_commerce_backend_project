package com.socialCommerce.backend_social.controller;

import com.socialCommerce.backend_social.model.BitlyRequest;
import com.socialCommerce.backend_social.service.BitlyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class BitlyRestController {

    @Autowired
    BitlyService bitlyService;

    @PostMapping("/processBitly")
    public String processBilty(@RequestBody BitlyRequest bitlyRequest){
       String shortURL = bitlyService.getShortURL(bitlyRequest.getLongURL());
    return shortURL;
    }

}
