package com.ecommhub.blog.category;

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
public class PostCategory {
    @Id
    @SequenceGenerator(
            name = "category_id_sequence",
            sequenceName = "category_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "category_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long categoryId;
    @NotBlank(message="Please provide the category name")
    private String name;
}
