package com.ecommhub.product;

import com.ecommhub.order.Order;
import com.ecommhub.product.fields.ProductAttribute;
import com.ecommhub.product.fields.SaleType;
import com.ecommhub.seasonal.Seasonal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_id_sequence",
            sequenceName = "product_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "product_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @NotBlank(message="Please provide product name")
    private String name;
    private String sku;
    private String metaDescription;
    private List<String> keywords;
    private String description;
    private Long image;
    private List<Long> imageGallery;
    private int price;
    private boolean isProductOnSale;
    @Enumerated(EnumType.STRING)
    private SaleType saleType;
    private int flatSale;
    private int salePercentage;
    @Embedded
    private ProductAttribute productAttribute;
    @ManyToOne
    @JoinColumn(
            name="product_brand_id",
            referencedColumnName = "id"
    )
    private ProductBrand productBrand;
    @ManyToOne
    @JoinColumn(
            name="product_category_id",
            referencedColumnName = "id"
    )
    private ProductCategory productCategory;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name="product_tags",
            joinColumns = {@JoinColumn(name="product_id")},
            inverseJoinColumns = {@JoinColumn(name="product_tag_id")}
    )
    private List<ProductTag> productTags;
    @ManyToOne
    @JoinColumn(
            name="seasonal_id",
            referencedColumnName = "id"
    )
    private Seasonal seasonal;

    @ManyToOne
    @JoinColumn(
            name="shop_id",
            referencedColumnName = "id"
    )
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


}
