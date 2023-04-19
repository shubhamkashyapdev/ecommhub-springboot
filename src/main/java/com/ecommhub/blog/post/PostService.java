package com.ecommhub.blog.post;

import com.ecommhub.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> fetchAllPosts();

    Post savePost(CreatePostDTO createPostDTO) throws NotFoundException;

    Post updatePostById(Long postId, UpdatePostDTO updatePostDTO) throws NotFoundException;

    Optional<Post> deletePostById(Long postId) throws NotFoundException;
}
