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
public class ProductTag {

    @Id
    @SequenceGenerator(
            name = "product_tag_id_sequence",
            sequenceName = "product_tag_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "product_tag_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @NotBlank(message = "Please provide product tag name")
    private String name;
}
