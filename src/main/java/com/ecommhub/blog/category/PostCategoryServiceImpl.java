package com.ecommhub.blog.category;

import com.ecommhub.blog.post.Post;
import com.ecommhub.error.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostCategoryServiceImpl implements PostCategoryService {

    private final PostCategoryRepository postCategoryRepository;

    public PostCategoryServiceImpl(PostCategoryRepository postCategoryRepository) {
        this.postCategoryRepository = postCategoryRepository;
    }

    @Override
    public PostCategory savePostCategory(CreatePostCategoryDTO postCategoryDTO) {
        PostCategory postCategory = PostCategory.builder()
                .name(postCategoryDTO.name())
                .build();
        return postCategoryRepository.save(postCategory);
    }

    @Override
    public List<PostCategory> fetchAllPostCategories() {
        return postCategoryRepository.findAll();
    }

    @Override
    public PostCategory deletePostCategoryById(Long postCategoryId) throws NotFoundException {
        Optional<PostCategory> postCategory = postCategoryRepository.findById(postCategoryId);
        if(!postCategory.isPresent()) {
           throw new NotFoundException("Post Category not found");
        }
        postCategoryRepository.deleteById(postCategoryId);
        return postCategory.get();
    }

    @Override
    public PostCategory updatePostCategoryById(Long postCategoryId, CreatePostCategoryDTO postCategoryDTO) throws NotFoundException {
        Optional<PostCategory> postCategory = postCategoryRepository.findById(postCategoryId);
        if(!postCategory.isPresent()) {
            throw new NotFoundException("Post Category not found");
        }
        PostCategory updatedPostCategory = postCategory.get();
        updatedPostCategory.setName(postCategoryDTO.name());
        return postCategoryRepository.save(updatedPostCategory);
    }
}
