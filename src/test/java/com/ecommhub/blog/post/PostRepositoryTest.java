package com.ecommhub.blog.post;

import com.ecommhub.blog.category.Category;
import com.ecommhub.blog.category.CategoryRepository;
import com.ecommhub.blog.tag.Tag;
import com.ecommhub.blog.tag.TagRepository;
import com.ecommhub.user.User;
import com.ecommhub.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void savePost(){
        // blog post author
        User user = User.builder()
                .firstName("Sanjeev")
                .lastName("Mishra")
                .email("sanjeev@gmail.com")
                .password("12345678")
                .build();
        User db_user = userRepository.save(user);
        // category for blog post
        Category category = Category.builder()
                .name("DSA")
                .build();
        Category db_category = categoryRepository.save(category);
        // tags for blog post
        List<Tag> tags=new ArrayList<Tag>();
        for(int i = 0; i < 5; i++){
            Tag tag = Tag.builder()
                    .name("Tag " + i)
                    .build();
            Tag db_tag = tagRepository.save(tag);
            tags.add(db_tag);
        }
        System.out.println(tags);
        Post post = Post.builder()
                .title("Learn DSA")
                .content("Learn DSA From Zero To Hero")
                .category(db_category)
                .author(db_user)
                .tags(tags)
                .build();
        Post post1 = Post.builder()
                .title("DSA Masterclass")
                .content("DSA Masterclass to step up your Developer Game")
                .category(db_category)
                .build();

        postRepository.save(post);
        postRepository.save(post1);
    }

    @Test
    public void fetchAllPost(){
        List<Post> posts = postRepository.findAll();
        System.out.println(posts);
    }

}