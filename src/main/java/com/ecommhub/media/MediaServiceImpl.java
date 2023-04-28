package com.ecommhub.media;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecommhub.error.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final Cloudinary cloudinary;

    public MediaServiceImpl(MediaRepository mediaRepository, Cloudinary cloudinary) {
        this.mediaRepository = mediaRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public ResponseEntity<List<Media>> fetchMedia() {
        return ResponseEntity.ok(mediaRepository.findAll());
    }

    @Override
    public ResponseEntity<Media> uploadMedia(MultipartFile multipart, String alt) throws IOException {
//        if(alt.isEmpty()) {
//            throw new InvalidParameterException("Please Provide Image ALT Text");
//        }
        try {
            Map options = ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", true,
                    "overwrite", true
            );
            Map cloud_media = cloudinary.uploader().upload(multipart.getBytes(), options);

            System.out.println("Media Details " + cloud_media.toString());

            String secure_url = cloud_media.get("secure_url").toString();
            int height = (int)cloud_media.get("height");
            int width = (int)cloud_media.get("width");
            String public_id = cloud_media.get("public_id").toString();

            // SAVE MEDIA IN COLLECTION
            Media media = Media.builder()
                    .url(secure_url)
                    .height(height)
                    .width(width)
                    .alt(alt)
                    .publicId(public_id)
                    .build();

            return ResponseEntity.ok(mediaRepository.save(media));
        }catch(IOException err){
            System.out.println("IO_EXCEPTION " + err);
        }
        return null;
    }

    @Override
    public Optional<Media> fetchMediaByPublicId(String public_id) throws Exception {
        Optional<Media> media = mediaRepository.findByPublicId(public_id);
        if(!media.isPresent()) {
            throw new NotFoundException("Media With Provided Public ID Not Found");
        }
        return media;
    }

    @Override
    public Media fetchMediaById(Long id) throws NotFoundException {
        Optional<Media> media = mediaRepository.findById(id);
        Media db_media = media.orElseThrow(() -> new NotFoundException("Media With Provided Media ID Not Found"));
        return db_media;
    }

    @Override
    public Media removeMediaById(Long id) throws NotFoundException, IOException {
        Optional<Media> media = mediaRepository.findById(id);
        Media db_media = media.orElseThrow(() -> new NotFoundException("Media With Provided Media ID Not Found"));
        // REMOVE FROM DB
        mediaRepository.deleteById(id);
        // REMOVE FROM CLOUDINARY
        cloudinary.uploader().destroy(db_media.getPublicId(), ObjectUtils.emptyMap());
        return db_media;
    }

    @Override
    public Media updateMediaById(Long id, MultipartFile multipartFile, String alt) throws NotFoundException, IOException {
        Optional<Media> media = mediaRepository.findById(id);
        Media db_media = media.orElseThrow(() -> new NotFoundException("Media With Provided Media ID Not Found"));

        // UPDATE MEDIA IN CLOUDINARY
        Map options = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", true,
                "overwrite", true
        );
        options.put("public_id", db_media.getPublicId());
        Map cloud_media = cloudinary.uploader().upload(multipartFile.getBytes(), options);

        System.out.println("Media Details " + cloud_media.toString());

        String secure_url = cloud_media.get("secure_url").toString();
        int height = (int)cloud_media.get("height");
        int width = (int)cloud_media.get("width");
        String public_id = cloud_media.get("public_id").toString();
        // UPDATE MEDIA IN DATABASE
        Media updatedMedia = media.get();
        updatedMedia.setUrl(secure_url);
        updatedMedia.setHeight(height);
        updatedMedia.setWidth(width);
        updatedMedia.setPublicId(public_id);

        if(!alt.isEmpty()) {
            updatedMedia.setAlt(alt);
        }

        return mediaRepository.save(updatedMedia);
    }


}
