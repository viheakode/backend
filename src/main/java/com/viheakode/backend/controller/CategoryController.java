package com.viheakode.backend.controller;

import com.viheakode.backend.dto.CategoryRequest;
import com.viheakode.backend.model.Category;
import com.viheakode.backend.service.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestBody CategoryRequest categoryRequest){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Created");
        response.put("data", categoryServiceImp.addCategory(categoryRequest));
        response.put("success", true);
        response.put("status", 201);
        return ResponseEntity.accepted().body(response);
    }

    @GetMapping
    public ResponseEntity<Object> getCategories(){
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success");
        response.put("data", categoryServiceImp.getCategories());
        response.put("success", true);
        response.put("status", 200);
        return ResponseEntity.ok().body(response);
    }
}
