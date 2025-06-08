package com.socialCommerce.backend_social.repo;

import com.socialCommerce.backend_social.model.ImageData;
import com.socialCommerce.backend_social.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT DISTINCT p FROM Product p JOIN FETCH p.imageDatas")
    List<Product> findAllWithImages();
}
