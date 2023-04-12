package com.ecommhub.blog.post;

import com.ecommhub.blog.category.PostCategory;
import com.ecommhub.blog.category.PostCategoryRepository;
import com.ecommhub.blog.tag.PostTag;
import com.ecommhub.blog.tag.TagRepository;
import com.ecommhub.user.User;
import com.ecommhub.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostCategoryRepository postCategoryRepository;
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
        PostCategory postCategory = PostCategory.builder()
                .name("DSA")
                .build();
        PostCategory db_Post_category = postCategoryRepository.save(postCategory);
        // tags for blog post
        List<PostTag> postTags =new ArrayList<PostTag>();
        for(int i = 0; i < 5; i++){
            PostTag postTag = PostTag.builder()
                    .name("Tag " + i)
                    .build();
            PostTag db_Post_tag = tagRepository.save(postTag);
            postTags.add(db_Post_tag);
        }
        System.out.println(postTags);
        Post post = Post.builder()
                .title("Learn DSA")
                .content("Learn DSA From Zero To Hero")
                .postCategory(db_Post_category)
                .author(db_user)
                .postTags(postTags)
                .build();
        Post post1 = Post.builder()
                .title("DSA Masterclass")
                .content("DSA Masterclass to step up your Developer Game")
                .postCategory(db_Post_category)
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