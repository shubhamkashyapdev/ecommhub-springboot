package com.ecommhub.media;

import com.ecommhub.error.NotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Tag(name="Media", description = "Media API")
@RestController
@RequestMapping("/api/v1/media")
@SecurityRequirement(name = "bearerAuth")
public class MediaController {
    private final MediaService mediaService;
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    public ResponseEntity<List<Media>> fetchMedia() {
        return mediaService.fetchMedia();
    }

    @PostMapping
    public ResponseEntity<Media> uploadMedia(@RequestParam("file")MultipartFile multipart, @RequestParam("alt") String alt) throws IOException {
        return mediaService.uploadMedia(multipart, alt);
    }

    @GetMapping("/public/{public_id}")
    public Optional<Media> getMediaByPublicId(@PathVariable("public_id") String public_id) throws Exception {
        return mediaService.fetchMediaByPublicId(public_id);
    }

    @GetMapping("{id}")
    public Media fetchMediaById(@PathVariable("id") Long id) throws NotFoundException {
        return mediaService.fetchMediaById(id);
    }

    @PutMapping("{id}")
    public Media updateMediaById(@PathVariable("id") Long id, @RequestParam("file") MultipartFile multipartFile, @RequestParam("alt") String alt) throws NotFoundException, IOException {
        return mediaService.updateMediaById(id, multipartFile, alt);
    }

    @DeleteMapping("{id}")
    public Media removeMediaById(@PathVariable("id") Long id) throws NotFoundException, IOException {
        return mediaService.removeMediaById(id);
    }
}
