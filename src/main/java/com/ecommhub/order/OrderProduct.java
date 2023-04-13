package com.ecommhub.order;

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
public class OrderProduct {
    @Id
    @SequenceGenerator(
            name = "order_product_id_sequence",
            sequenceName = "order_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "order_product_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name="order_id",
            referencedColumnName = "id"
    )
    private Order order;

    @ManyToOne
    @JoinColumn(
            name="product_id",
            referencedColumnName = "id"
    )
    private Product product;
    private int quantity;
    private int price;
}
