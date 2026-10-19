package com.MyImageUpload.controller;

import com.MyImageUpload.model.ContactUs;
import com.MyImageUpload.model.Image;
import com.MyImageUpload.repository.ContactUsRepository;
import com.MyImageUpload.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    @Autowired
    private final ContactUsRepository contactUsRepository;

    public ContactUsController(ContactUsRepository contactUsRepository) {
        this.contactUsRepository = contactUsRepository;
    }

    @PostMapping("/contact")
    @ResponseStatus(HttpStatus.CREATED)
    public String submitContactForm(@RequestBody ContactUs contactUs) {
        return contactUsService.handleContactUs(contactUs);
    }

    //Regular way without pagination
    /**
    @GetMapping("allContact")
    public List<ContactUs> getAllContact() {
        return contactUsRepository.findAll();
    }

     */
    //Pagination way to get data
    @GetMapping("/allContact")
    public Page<ContactUs> getContactPagination(
            @RequestParam(defaultValue = "0") int page, //default page starts at 0
            @RequestParam(defaultValue = "12") int size //default to 20 images per page
    )
    {
        return contactUsService.getAllContact(page, size);

    }
}
