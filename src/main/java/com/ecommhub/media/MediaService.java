package com.ecommhub.media;

import com.ecommhub.error.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MediaService {

    ResponseEntity<List<Media>> fetchMedia();

    ResponseEntity<Media> uploadMedia(MultipartFile multipart, String alt) throws IOException;

    Optional<Media> fetchMediaByPublicId(String public_id) throws Exception;

    Media fetchMediaById(Long id) throws NotFoundException;

    Media removeMediaById(Long id) throws NotFoundException, IOException;

    Media updateMediaById(Long id, MultipartFile multipartFile, String alt) throws NotFoundException, IOException;
}
