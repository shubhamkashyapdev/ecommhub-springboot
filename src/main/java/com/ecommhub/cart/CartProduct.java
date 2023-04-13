package com.ecommhub.cart;

import com.ecommhub.order.Order;
import com.ecommhub.product.Product;
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
public class CartProduct {
    @Id
    @SequenceGenerator(
            name = "cart_product_id_sequence",
            sequenceName = "cart_product_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "cart_product_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name="product_id",
            referencedColumnName = "id"
    )
    private Product product;

    private int quantity;
    private int price;
}
