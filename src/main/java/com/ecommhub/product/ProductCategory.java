package com.ecommhub.product;

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
public class ProductCategory {
    @Id
    @SequenceGenerator(
            name = "product_category_id_sequence",
            sequenceName = "product_category_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "product_category_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @NotBlank(message="Please provide category name")
    private String name;
    private String image;
}
