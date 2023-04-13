package com.ecommhub.media;


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
public class Media {

    @Id
    @SequenceGenerator(
            name = "media_id_sequence",
            sequenceName = "media_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "media_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String url;
    private int height;
    private int width;
    private String alt;
}
