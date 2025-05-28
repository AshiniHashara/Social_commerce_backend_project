package com.socialCommerce.backend_social.repo;

import com.socialCommerce.backend_social.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    
}
