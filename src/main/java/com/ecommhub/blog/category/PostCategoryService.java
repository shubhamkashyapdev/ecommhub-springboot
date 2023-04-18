package com.ecommhub.blog.category;

import com.ecommhub.error.NotFoundException;

import java.util.List;

public interface PostCategoryService {
    PostCategory savePostCategory(CreatePostCategoryDTO postCategoryDTO);

    List<PostCategory> fetchAllPostCategories();

    PostCategory deletePostCategoryById(Long postCategoryId) throws NotFoundException;

    PostCategory updatePostCategoryById(Long postCategoryId, CreatePostCategoryDTO postCategoryDTO) throws NotFoundException;
}
