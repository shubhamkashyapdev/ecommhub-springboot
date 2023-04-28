package com.ecommhub.product;

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
public class Shop {
    @Id
    @SequenceGenerator(
            name = "shop_id_sequence",
            sequenceName = "shop_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "shop_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;
    private boolean isActive;
    private String description;
    private String address;
    private Long coverImage;
    private String logo;
    private String mobileNumber;
    private String website;
}
