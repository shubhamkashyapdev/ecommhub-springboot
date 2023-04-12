package com.ecommhub.blog.tag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PostTagRepositoryTest {
    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void saveTag(){
        PostTag postTag1 = PostTag.builder()
                .name("Javascript")
                .build();
        PostTag postTag2 = PostTag.builder()
                .name("Javascript")
                .build();
        tagRepository.save(postTag1);
        tagRepository.save(postTag2);
        List<PostTag> postTags = tagRepository.findAll();
        System.out.println(postTags);
    }

    @Test
    public void fetchAllTags(){
        List<PostTag> postTags = tagRepository.findAll();
        System.out.println(postTags);
    }
}