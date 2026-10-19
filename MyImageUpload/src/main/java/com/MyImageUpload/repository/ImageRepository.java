package com.MyImageUpload.repository;

import com.MyImageUpload.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    // Method to find image by URL
    Optional<Image> findByUrl(String url);

    // Method to delete image by URL
    void deleteByUrl(String url);


}
