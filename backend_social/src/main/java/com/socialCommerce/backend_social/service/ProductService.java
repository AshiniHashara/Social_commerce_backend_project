package com.socialCommerce.backend_social.service;

import com.socialCommerce.backend_social.model.Product;
import com.socialCommerce.backend_social.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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

//    public Optional<Product> getProductbyId(int id) {
//        return repo.findById(id);
//    }

//    public Product getProductbyId(int id) {
//        return repo.findById(id);
//
//    }

    public Product getProductByIdWithImage(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    //get all products with image
    public List<Product> getAllProductsWithImage() {
        return repo.findAll(); // Will fetch with images due to EAGER fetch
    }

}
