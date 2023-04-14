package com.ecommhub.cart;

import com.ecommhub.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name="user_id",
            referencedColumnName = "id",
            nullable = false
    )
    private User user;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.EAGER
    )
    private List<CartProduct> cartProducts;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", cartProducts=" + cartProducts +
                '}';
    }
}
