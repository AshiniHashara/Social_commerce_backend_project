package com.socialCommerce.backend_social.service;

import com.socialCommerce.backend_social.model.ImageData;
import com.socialCommerce.backend_social.model.Product;
import com.socialCommerce.backend_social.repo.ImageDataRepo;
import com.socialCommerce.backend_social.repo.ProductRepo;
import com.socialCommerce.backend_social.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {
    @Autowired
    private ImageDataRepo repo;

    @Autowired
    private ProductRepo productRepo;


    public String uploadImage(MultipartFile file, Long productId) throws IOException {
        // Find the product by ID
        Product product = productRepo.findById(Math.toIntExact(productId))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ImageData imageData = repo.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .product(product)  // Set full Product object here
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());

        return "File uploaded successfully: " + file.getOriginalFilename();
    }


    public byte[] downloadImage(Long id){
        Optional<ImageData> dbimageData = repo.findById(id);
        byte[] images = ImageUtils.decompressImage(dbimageData.get().getImageData());
        return images;
    }
}
