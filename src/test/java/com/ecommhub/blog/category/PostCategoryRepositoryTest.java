package com.ecommhub.blog.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PostCategoryRepositoryTest {

    @Autowired
    private PostCategoryRepository postCategoryRepository;

    @Test
    public void saveCategory(){
        PostCategory postCategory = PostCategory.builder()
                .name("DSA")
                .build();
        postCategoryRepository.save(postCategory);
    }

    @Test
    public void fetchAllCategories(){
        List<PostCategory> categories= postCategoryRepository.findAll();
        System.out.println(categories);
    }

}