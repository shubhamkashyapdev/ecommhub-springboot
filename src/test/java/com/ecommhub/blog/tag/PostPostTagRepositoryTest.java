package com.ecommhub.blog.tag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PostPostTagRepositoryTest {
    @Autowired
    private PostTagRepository postTagRepository;

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
        postTagRepository.save(postTag1);
        postTagRepository.save(postTag2);
        List<PostTag> postTags = postTagRepository.findAll();
        System.out.println(postTags);
    }

    @Test
    public void fetchAllTags(){
        List<PostTag> postTags = postTagRepository.findAll();
        System.out.println(postTags);
    }
}