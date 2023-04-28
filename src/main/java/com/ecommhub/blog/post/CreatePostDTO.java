package com.ecommhub.blog.post;

import com.ecommhub.blog.category.PostCategory;
import com.ecommhub.blog.tag.PostTag;
import com.ecommhub.user.User;

import java.util.List;

public record CreatePostDTO(String title, String content, String excerpt, Long featuredImage, Long author, Long category, List<Long> postTags) {
}
