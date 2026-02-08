package com.viheakode.backend.controller;

import com.viheakode.backend.dto.CategoryRequest;
import com.viheakode.backend.model.Category;
import com.viheakode.backend.service.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @PostMapping
    public Category addCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryServiceImp.addCategory(categoryRequest);
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryServiceImp.getCategories();
    }
}
