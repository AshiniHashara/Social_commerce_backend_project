package com.socialCommerce.backend_social.service;

import com.socialCommerce.backend_social.model.ImageData;
import com.socialCommerce.backend_social.model.Product;
import com.socialCommerce.backend_social.model.ProductDTO;
import com.socialCommerce.backend_social.repo.ProductRepo;
import com.socialCommerce.backend_social.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Base64;

@Service
public class ProductService {
     @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {

        return repo.findAll();
    }

    public Product addProduct(Product product) throws IOException {

        repo.save(product);
        return product;
    }


    public Product getProductById(int id) {
        return repo.findById(id).orElse(new Product());
    }

    public Product getProductByIdWithImage(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    //get all products with image
    public List<Product> getAllProductsWithImage() {
        return repo.findAll(); // Will fetch with images due to EAGER fetch
    }

    public List<ProductDTO> getAllProductDTOs() {
        List<Product> products = repo.findAllWithImages();
        return products.stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setDescription(product.getDescription());
            // Set other fields...

            List<String> imageStrings = product.getImageDatas().stream()
                    .map(image -> "data:" + image.getType() + ";base64," +
                            Base64.getEncoder().encodeToString(image.getImageData()))
                    .collect(Collectors.toList());
            List<String> imageIDs = product.getImageDatas().stream()
                    .map(image -> String.valueOf(image.getId()))
                    .collect(Collectors.toList());

            dto.setImageUrls(imageStrings);
            dto.setimageID(imageIDs);
            return dto;
        }).collect(Collectors.toList());
    }




}
