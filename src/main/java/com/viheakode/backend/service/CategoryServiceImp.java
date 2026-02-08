package com.viheakode.backend.service;

import com.viheakode.backend.dto.CategoryRequest;
import com.viheakode.backend.model.Category;
import com.viheakode.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setDescription(categoryRequest.getDescription());
        return categoryRepository.save(category);
    }
}
