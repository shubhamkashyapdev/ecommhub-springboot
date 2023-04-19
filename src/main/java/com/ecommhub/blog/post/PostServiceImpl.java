package com.ecommhub.blog.post;

import com.ecommhub.blog.category.PostCategory;
import com.ecommhub.blog.category.PostCategoryRepository;
import com.ecommhub.blog.tag.PostTag;
import com.ecommhub.blog.tag.PostTagRepository;
import com.ecommhub.error.NotFoundException;
import com.ecommhub.user.User;
import com.ecommhub.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final UserRepository userRepository;
    private final PostCategoryRepository postCategoryRepository;

    public PostServiceImpl(PostRepository postRepository, PostTagRepository postTagRepository, UserRepository userRepository, PostCategoryRepository postCategoryRepository) {
        this.postRepository = postRepository;
        this.postTagRepository = postTagRepository;
        this.userRepository = userRepository;
        this.postCategoryRepository = postCategoryRepository;
    }

    @Override
    public List<Post> fetchAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post savePost(CreatePostDTO createPostDTO) throws NotFoundException {
        Optional<User> user = userRepository.findById(createPostDTO.author());
        User author = user.orElseThrow(() -> new NotFoundException("User Not Found By Provided User ID"));

        // category
        Optional<PostCategory> postCategory = postCategoryRepository.findById(createPostDTO.category());
        PostCategory category = postCategory.orElseThrow(() -> new NotFoundException("User Not Found By Provided User ID"));

        // tags
        List<PostTag> tags = new ArrayList<>();
        List<PostTag> tagList = postTagRepository.findAllById(createPostDTO.postTags());
        if (tagList.size() < createPostDTO.postTags().size()) {
            throw new NotFoundException("One or more tags not found");
        }
        tags.addAll(tagList);


        Post post = Post.builder()
                .title(createPostDTO.title())
                .content(createPostDTO.content())
                .excerpt(createPostDTO.excerpt())
                .featuredImage(createPostDTO.featuredImage())
                .author(author)
                .postCategory(category)
                .postTags(tags)
                .build();
        return postRepository.save(post);
    }

    @Override
    public Post updatePostById(Long postId, UpdatePostDTO updatePostDTO) throws NotFoundException {

        Optional<Post> post = postRepository.findById(postId);
        if(!post.isPresent()){
            throw new NotFoundException("Post Not Found By Provided ID");
        }
        Post updatedPost = post.get();
        updatedPost.setTitle(updatePostDTO.title());
        updatedPost.setContent(updatedPost.getContent());
        updatedPost.setExcerpt(updatePostDTO.excerpt());
        updatedPost.setFeaturedImage(updatePostDTO.featuredImage());
        return postRepository.save(updatedPost);
    }

    @Override
    public Optional<Post> deletePostById(Long postId) throws NotFoundException {
        Optional<Post> post = postRepository.findById(postId);
        if(!post.isPresent()){
            throw new NotFoundException("Post Not Found By Provided ID");
        }
        postRepository.deleteById(postId);
        return post;
    }
}
