package com.MyImageUpload.service;

import com.MyImageUpload.model.Image;
import com.MyImageUpload.repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileUploadService {

    // Directory for images
    //private static final String IMAGE_DIRECTORY = "/Users/abrahamparker/MyUploads";

    //For Production
   private static final String IMAGE_DIRECTORY = "/var/www/abrahamparker/MyUploads";
    //Below line for Production
    @Value("/var/www/abrahamparker/MyUploads")
    //Below for local
    //@Value("/Users/abrahamparker/MyUploads")
    private String uploadDir;

   @Autowired
    private final ImageRepository imageRepository;

    public FileUploadService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    //Method to uploadImage
    public String uploadImage(MultipartFile file) throws IOException {
        //Generate a unique file name
        //String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String fileName = file.getOriginalFilename();

        //Create the file path
        Path path = Paths.get(uploadDir).resolve(fileName);

        //Create directory if not exist
        Files.createDirectories(path.getParent());

        //Finally save the image information in the server
        Files.copy(file.getInputStream(), path);

        //Save the image information in the database
        Date uploadDateTime = new Date();
        Image image = new Image();

        image.setId(UUID.randomUUID().toString());
        image.setName(fileName);
        //For production
        image.setUrl("https://www.abrahamparker.com/MyUploads/" + fileName);
        //For LocalTest
        //image.setUrl("http://localhost:8082/MyUploads/" + fileName);
        image.setCurrentDate(uploadDateTime);
        imageRepository.save(image);
        //Do not want to show the file location
        //return "Successfully uploaded " + image.getUrl();
        return "Your File '" + image.getName() + "' Successfully uploaded.";


    }

    public Page<Image> getAll(int page, int size) {
       Pageable pageable = PageRequest.of(page, size);
        return imageRepository.findAll(pageable);
    }

    public Page<Image>getAllImages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return imageRepository.findAll(pageable);
    }

    //Method to delete the image by URL
    // Mark the method as @Transactional to ensure the operation is done within a transaction
    @Transactional
    public String deleteImageByUrl(String url) {
       // Step 1: Find image from the database
        Optional<Image> optionalImage = imageRepository.findByUrl(url);

        //Check if the image exists in the database
        if(!optionalImage.isPresent()){
            return "Image not found in the database";
        }
        Image image = optionalImage.get();

        //Delete the image from the Server
        try {
            Path imagePath = Paths.get(IMAGE_DIRECTORY, image.getName());
            File file = imagePath.toFile();

            if(file.exists() && file.isFile()){
                //Delete the file from the server
                boolean isDeleted = file.delete();
                if(isDeleted) {
                    //Step 3: Remove the URL from the database
                    imageRepository.deleteByUrl(url);
                    //return "Image file and URL deleted successfully";
                    return "The '" + image.getName() + "' deleted successfully.";
                } else {
                    return "Failed to delete image file.";
                }
            } else {
                return "Image file not found on the Server";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while deleting image file: " + e.getMessage();
        }

    }

}
