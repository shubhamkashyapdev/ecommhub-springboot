package com.ecommhub.blog.category;

import com.ecommhub.error.NotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Post Category", description = "Post Category API")
@RestController
@RequestMapping("/api/v1/category")
@SecurityRequirement(name = "bearerAuth")
public class PostCategoryController {

    private final PostCategoryService postCategoryService;

    public PostCategoryController(PostCategoryService postCategoryService) {
        this.postCategoryService = postCategoryService;
    }

    @PostMapping
    public PostCategory savePostCategory(@RequestBody() CreatePostCategoryDTO postCategoryDTO){
        return postCategoryService.savePostCategory(postCategoryDTO);
    }

    @GetMapping
    public List<PostCategory> fetchAllPostCategories(){
        return postCategoryService.fetchAllPostCategories();
    }

    @PutMapping("{id}")
    public PostCategory updatePostCategoryById(@PathVariable("id") Long postCategoryId, @RequestBody() CreatePostCategoryDTO postCategoryDTO) throws NotFoundException {
        return postCategoryService.updatePostCategoryById(postCategoryId, postCategoryDTO);
    }

    @DeleteMapping("{id}")
    public PostCategory deletePostCategoryById(@PathVariable("id") Long postCategoryId) throws NotFoundException {
        return postCategoryService.deletePostCategoryById(postCategoryId);
    }
}
