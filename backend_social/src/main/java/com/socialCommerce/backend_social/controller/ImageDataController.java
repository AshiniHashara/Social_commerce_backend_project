package com.socialCommerce.backend_social.controller;

import com.socialCommerce.backend_social.service.ImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
@RestController
public class ImageDataController {
    @Autowired
    private ImageDataService service;

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,@RequestParam("productId") Long productId) throws IOException {
        String uploadImage = service.uploadImage(file, productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    //pass the id and get image
    @GetMapping("/image/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Long id){
        byte[] imageData=service.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

}
