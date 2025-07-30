package com.socialCommerce.backend_social.service;

import com.socialCommerce.backend_social.model.Category;
import com.socialCommerce.backend_social.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    public Category addCategory(Category category) throws IOException {
        return categoryRepo.save(category);
    }

    public Category getCategoryById(int id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
    }
}
