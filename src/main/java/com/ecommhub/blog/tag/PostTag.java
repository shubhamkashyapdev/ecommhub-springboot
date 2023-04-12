package com.ecommhub.blog.tag;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostTag {
    @Id
    @SequenceGenerator(
            name = "tags_id_sequence",
            sequenceName = "tags_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "tags_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
}
