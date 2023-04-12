package com.ecommhub.seasonal;

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
public class Seasonal {
    @Id
    @SequenceGenerator(
            name = "seasonal_id_sequence",
            sequenceName = "seasonal_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "seasonal_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
}
