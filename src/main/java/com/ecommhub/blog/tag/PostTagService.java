package com.ecommhub.blog.tag;

import com.ecommhub.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface PostTagService {
    List<PostTag> fetchAllPostTags();

    PostTag savePostTag(PostTagDTO postTagDTO);

    Optional<PostTag> deletePostTagById(Long tagId) throws NotFoundException;

    PostTag updatePostTagById(Long tagId, PostTagDTO postTagDTO) throws NotFoundException;
}
