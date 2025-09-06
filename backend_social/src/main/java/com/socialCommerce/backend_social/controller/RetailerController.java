package com.socialCommerce.backend_social.controller;

import com.socialCommerce.backend_social.model.Category;
import com.socialCommerce.backend_social.model.Retailer;
import com.socialCommerce.backend_social.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class RetailerController {

    @Autowired
    private RetailerService retailerService;

    @PostMapping("/retailer/margin")
    public ResponseEntity<?> addRetailerDetails(@RequestBody Retailer retailer) {
        try {
            Retailer savedRetailer = retailerService.addRetailer(retailer);
            return new ResponseEntity<>(savedRetailer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retailer")
    public ResponseEntity<List<Retailer>> getAllRetailerDetails() {
        List<Retailer> retailer = retailerService.getAllRetailerDetails();
        return ResponseEntity.ok(retailer);
    }
}
