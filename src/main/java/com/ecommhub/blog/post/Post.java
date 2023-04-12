package com.ecommhub.blog.post;

import com.ecommhub.blog.category.PostCategory;
import com.ecommhub.blog.tag.PostTag;
import com.ecommhub.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @SequenceGenerator(
            name = "post_id_sequence",
            sequenceName = "post_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "post_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long postId;
    private String featuredImage;
    private String title;
    private String content;
    private String excerpt;
    // @todo
    private java.sql.Timestamp publishedOn;

    @ManyToOne
    @JoinColumn(
            name="author_id",
            referencedColumnName = "id"
    )
    private User author;

    @ManyToOne
    @JoinColumn(
            name="category_id",
            referencedColumnName = "categoryId"
    )
    private PostCategory postCategory;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name="post_tags",
            joinColumns = {@JoinColumn(name="post_id")},
            inverseJoinColumns = {@JoinColumn(name="tag_id")}
    )
    private List<PostTag> postTags;

    // @todo - timestamps to be added
}
