package com.socialCommerce.backend_social.service;

import com.socialCommerce.backend_social.model.Product;
import com.socialCommerce.backend_social.model.Retailer;
import com.socialCommerce.backend_social.model.UniqueLink;
import com.socialCommerce.backend_social.repo.ProductRepo;
import com.socialCommerce.backend_social.repo.RetailerRepo;
import com.socialCommerce.backend_social.repo.UniqueLinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UniqueLinkService {
    @Autowired
    private UniqueLinkRepo uniqueLinkRepo;

    public UniqueLink saveUniqueLink(UniqueLink uniqueLink) {
        return uniqueLinkRepo.save(uniqueLink);
    }
}
