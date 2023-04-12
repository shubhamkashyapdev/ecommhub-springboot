package com.ecommhub.cart;

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
}
