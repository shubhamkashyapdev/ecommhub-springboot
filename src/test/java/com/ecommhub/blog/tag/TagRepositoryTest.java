package com.ecommhub.blog.tag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TagRepositoryTest {
    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void saveTag(){
        Tag tag1 = Tag.builder()
                .name("Javascript")
                .build();
        Tag tag2 = Tag.builder()
                .name("Javascript")
                .build();
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        List<Tag> tags = tagRepository.findAll();
        System.out.println(tags);
    }

    @Test
    public void fetchAllTags(){
        List<Tag> tags = tagRepository.findAll();
        System.out.println(tags);
    }
}