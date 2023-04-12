package com.ecommhub.product.fields;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAttribute {
    @Enumerated(EnumType.STRING)
    private List<ProductSize> productSizes;
    @ElementCollection(
            fetch = FetchType.EAGER
    )
    @CollectionTable(name = "product_color", joinColumns = @JoinColumn(name = "product_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "color_name")),
            @AttributeOverride(name = "hexcode", column = @Column(name = "color_hexcode"))
    })
    private List<ProductColor> productColors;
}
