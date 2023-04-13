package com.ecommhub.cart;

import com.ecommhub.user.User;
import jakarta.persistence.*;
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
public class Cart {
    @Id
    @SequenceGenerator(
            name = "cart_id_sequence",
            sequenceName = "cart_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "cart_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @OneToOne
    @JoinColumn(
            name="user_id",
            referencedColumnName = "id"
    )
    private User user;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="cart_product_id",
            referencedColumnName = "id"
    )
    private List<CartProduct> cartProducts;
}
