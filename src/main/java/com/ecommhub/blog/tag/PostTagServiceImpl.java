package com.ecommhub.blog.tag;

import com.ecommhub.error.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostTagServiceImpl implements PostTagService {

    private final PostTagRepository postTagRepository;

    public PostTagServiceImpl(PostTagRepository postTagRepository) {
        this.postTagRepository = postTagRepository;
    }

    @Override
    public List<PostTag> fetchAllPostTags() {
        return postTagRepository.findAll();
    }

    @Override
    public PostTag savePostTag(PostTagDTO postTagDTO) {
        PostTag postTag = PostTag.builder()
                .name(postTagDTO.name())
                .build();
        return postTagRepository.save(postTag);
    }

    @Override
    public Optional<PostTag> deletePostTagById(Long tagId) throws NotFoundException {
        Optional<PostTag> postTag = postTagRepository.findById(tagId);
        if(!postTag.isPresent()){
            throw new NotFoundException("Post tag not found by provided ID");
        }
        postTagRepository.deleteById(tagId);
        return postTag;
    }

    @Override
    public PostTag updatePostTagById(Long tagId, PostTagDTO postTagDTO) throws NotFoundException {
        Optional<PostTag> postTag = postTagRepository.findById(tagId);
        if(!postTag.isPresent()){
            throw new NotFoundException("Post tag not found by provided ID");
        }
        PostTag updatedPostTag = postTag.get();
        updatedPostTag.setName(postTagDTO.name());
        return postTagRepository.save(updatedPostTag);
    }
}
