package com.ecommhub.cart;

import com.ecommhub.order.Order;
import com.ecommhub.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne
    @JoinColumn(
            name="product_id",
            referencedColumnName = "id"
    )
    private Product product;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name="cart_id",
            referencedColumnName = "id"
    )
    @JsonIgnore
    private Cart cart;

    @JsonProperty("cartId")
    public Long getCartId() {
        return cart != null ? cart.getId() : null;
    }

    private int quantity;

    @Override
    public String toString() {
        return "CartProduct{" +
                "id=" + id +
                ", product=" + product.getId() +
                ", cart=" + cart.getId() +
                ", quantity=" + quantity +
                '}';
    }
}
