package com.MyImageUpload.controller;


import com.MyImageUpload.model.Image;
import com.MyImageUpload.repository.ImageRepository;
import com.MyImageUpload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
//Using CorssOrigin now globally on config-> WebConfig
//@CrossOrigin(origins = {
//        "https://www.abrahamparker.com",
//        "https://abrahamparker.com",
//        "https://www.probasalapon.com",
//        "https://probasalapon.com"
//    })
//@CrossOrigin(origins = "*") // Allow Vue app to access API
//@CrossOrigin(origins="http://localhost:8080")
@RequestMapping("/api/images")

public class ImageController {

    private final FileUploadService fileUploadService;
    @Autowired
    private final ImageRepository imageRepository;

    public ImageController(FileUploadService fileUploadService,
                           ImageRepository imageRepository) {
        this.fileUploadService = fileUploadService;
        this.imageRepository = imageRepository;
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file")MultipartFile file) throws IOException {
        return fileUploadService.uploadImage(file);
    }

    @GetMapping("/listAll")
    public Page<Image> getAllLocation(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return fileUploadService.getAll(page, size);
    }

    //Regular way to do that
    @GetMapping("findAll")
    List<Image> getAllImages() {
        return imageRepository.findAll();
    }
    //Let's make pagination
    @GetMapping("/findAllPagination")
    public Page<Image> getImagesPagination(
        @RequestParam(defaultValue = "0") int page, //default page starts at 0
        @RequestParam(defaultValue = "12") int size //default to 20 images per page
        ){
        return fileUploadService.getAllImages(page, size);
    }

    //Now I like to delete, Endpoint to delete image by URL
    @DeleteMapping("delete")
    public String deleteImage (@RequestParam String url) {
        return fileUploadService.deleteImageByUrl(url);
    }



}
