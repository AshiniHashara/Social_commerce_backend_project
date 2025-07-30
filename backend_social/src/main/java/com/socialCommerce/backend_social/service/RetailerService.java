package com.socialCommerce.backend_social.service;

import com.socialCommerce.backend_social.model.Category;
import com.socialCommerce.backend_social.model.Retailer;
import com.socialCommerce.backend_social.repo.RetailerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RetailerService {

    @Autowired
    private RetailerRepo retailerRepo;

    public List<Retailer> getAllRetailerDetails() {
        return retailerRepo.findAll();
    }

    public Retailer addRetailer(Retailer retailer) throws IOException {
        return retailerRepo.save(retailer);
    }
}
