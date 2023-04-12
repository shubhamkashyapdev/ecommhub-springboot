package com.ecommhub.payment;

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
public class Payment {
    @Id
    @SequenceGenerator(
            name = "payment_id_sequence",
            sequenceName = "payment_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "payment_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
}
