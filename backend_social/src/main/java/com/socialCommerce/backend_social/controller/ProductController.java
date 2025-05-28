package com.socialCommerce.backend_social.controller;

import com.socialCommerce.backend_social.model.Product;
import com.socialCommerce.backend_social.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

//    @PostMapping("/product")
//    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
//        try {
//            Product product1 = service.addProduct(product, imageFile);
//            return new ResponseEntity<>(product1, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//
//
//        }
//    }

@PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        try {
            Product product1 = service.addProduct(product);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id){
        return service.getProductById(id);
    }

//get product details with image
    @GetMapping("/product/image/{id}")
    public ResponseEntity<Product> getProductWithImages(@PathVariable int id) {
        Product product = service.getProductByIdWithImage(id);
        return ResponseEntity.ok(product);
    }

    //get all products with image
    @GetMapping("/product/image")
    public ResponseEntity<List<Product>> getAllProductsWithImage() {
        List<Product> products = service.getAllProductsWithImage();
        return ResponseEntity.ok(products);
    }

//    public Product getProductById(){
//        return service.getProductById();
//    }

//    @GetMapping("/product/{id}")
//    public Optional<Product> getProduct(@PathVariable int id){
//        return service.getProductbyId(id);
//    }
}
