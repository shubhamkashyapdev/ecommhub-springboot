package com.ecommhub.media;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MediaRepositoryTest {

    @Autowired
    private MediaRepository mediaRepository;


    @Test
    public void saveMedia(){
        Media media1 = Media.builder()
                .url("https://images/dummy_image_1.jpg")
                .height(600)
                .width(500)
                .alt("Dummy Image 1")
                .build();
        Media db_media_1 = mediaRepository.save(media1);
        Media media2 = Media.builder()
                .url("https://images/dummy_image_2.jpg")
                .height(600)
                .width(500)
                .alt("Dummy Image 2")
                .build();
        Media db_media_2 = mediaRepository.save(media2);
        System.out.println(List.of(db_media_1, db_media_2));
    }

    @Test
    public void printAllMediaFiles(){
        List<Media> medias = mediaRepository.findAll();
        System.out.println(medias);
    }

}