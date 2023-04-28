package com.ecommhub.blog.tag;

import com.ecommhub.error.NotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name="Post Tag", description = "Post Tag API")
@RestController
@RequestMapping("/api/v1/post-tag")
@SecurityRequirement(name = "bearerAuth")
public class PostTagController {
    private final PostTagService postTagService;

    public PostTagController(PostTagService postTagService) {
        this.postTagService = postTagService;
    }

    @GetMapping
    public List<PostTag> fetchAllPostTags(){
        return postTagService.fetchAllPostTags();
    }

    @PostMapping
    public PostTag savePostTag(@RequestBody() PostTagDTO postTagDTO){
        return postTagService.savePostTag(postTagDTO);
    }

    @PutMapping("{tagId}")
    public PostTag updatePostTagById(@RequestBody() PostTagDTO postTagDTO, @PathVariable("tagId") Long tagId) throws NotFoundException {
        return postTagService.updatePostTagById(tagId, postTagDTO);
    }

    @DeleteMapping("{tagId}")
    public Optional<PostTag> deletePostTagById(@PathVariable("tagId") Long tagId) throws NotFoundException {
        return postTagService.deletePostTagById(tagId);
    }
}
