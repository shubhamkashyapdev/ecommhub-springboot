package com.ecommhub.blog.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void saveCategory(){
        Category category = Category.builder()
                .name("DSA")
                .build();
        categoryRepository.save(category);
    }

    @Test
    public void fetchAllCategories(){
        List<Category> categories= categoryRepository.findAll();
        System.out.println(categories);
    }

}