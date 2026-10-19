package com.MyImageUpload.service;

import com.MyImageUpload.model.ContactUs;
import com.MyImageUpload.model.Image;
import com.MyImageUpload.repository.ContactUsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ContactUsService {

    private final ContactUsRepository contactUsRepository;

    public ContactUsService(ContactUsRepository contactUsRepository) {
        this.contactUsRepository = contactUsRepository;
    }

    // This is a placeholder method to demonstrate service logic
    public String handleContactUs(ContactUs contactUs) {

        contactUsRepository.save(contactUs);
        // Here you could add code to save the contact information to a database or send it as an email.
        System.out.println("Received contact request: " + contactUs);
        return "Thank you for reaching out " + contactUs.getName() + "! We will get back to you soon.";
    }

    public Page<ContactUs> getAllContact(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return contactUsRepository.findAll(pageable);
    }

}
