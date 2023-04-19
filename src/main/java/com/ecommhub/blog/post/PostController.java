package com.ecommhub.blog.post;

import com.ecommhub.error.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> fetchAllPosts(){
        return postService.fetchAllPosts();
    }

    @PostMapping
    public Post savePost(@RequestBody() CreatePostDTO createPostDTO) throws NotFoundException {
        return postService.savePost(createPostDTO);
    }

    @PutMapping("{postId}")
    public Post updatePostById(@PathVariable("postId") Long postId, @RequestBody() UpdatePostDTO updatePostDTO) throws NotFoundException {
        return postService.updatePostById(postId, updatePostDTO);
    }

    @DeleteMapping("{postId}")
    public Optional<Post> deletePost(@PathVariable("postId") Long postId) throws NotFoundException {
        return postService.deletePostById(postId);
    }
}
