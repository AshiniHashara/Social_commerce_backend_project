package com.socialCommerce.backend_social.repo;

import com.socialCommerce.backend_social.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageDataRepo extends JpaRepository<ImageData,Long> {
    Optional<ImageData> findByName(String fileName);
}
