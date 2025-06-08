package com.socialCommerce.backend_social.repo;

import com.socialCommerce.backend_social.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
