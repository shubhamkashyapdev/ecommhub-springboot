package com.ecommhub.blog.tag;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message="Please Provide The Post Tag Name")
    private String name;
}
