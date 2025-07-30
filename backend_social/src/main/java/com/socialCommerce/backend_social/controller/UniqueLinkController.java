package com.socialCommerce.backend_social.controller;

import com.socialCommerce.backend_social.model.Product;
import com.socialCommerce.backend_social.model.Retailer;
import com.socialCommerce.backend_social.model.UniqueLink;
import com.socialCommerce.backend_social.model.UniqueLinkRequestDTO;
import com.socialCommerce.backend_social.repo.ProductRepo;
import com.socialCommerce.backend_social.repo.RetailerRepo;
import com.socialCommerce.backend_social.service.UniqueLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class UniqueLinkController {

    @Autowired
    private UniqueLinkService uniqueLinkService;

    @Autowired
    private RetailerRepo retailerRepo;

    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/uniqueLink")
    public ResponseEntity<?> createUniqueLink(@RequestBody UniqueLinkRequestDTO request) {
        Optional<Retailer> retailerOpt = retailerRepo.findById(request.getRetailerId());
        Optional<Product> productOpt = productRepo.findById(request.getProductId());

        if (retailerOpt.isEmpty() || productOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Retailer or Product not found");
        }

        UniqueLink uniqueLink = new UniqueLink();
        uniqueLink.setUnique_link(request.getUniqueLink());
        uniqueLink.setRetailer(retailerOpt.get());
        uniqueLink.setProduct(productOpt.get());

        UniqueLink savedLink = uniqueLinkService.saveUniqueLink(uniqueLink);
        return ResponseEntity.ok(savedLink);
    }
}
