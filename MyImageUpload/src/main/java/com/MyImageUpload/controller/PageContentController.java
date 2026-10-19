package com.MyImageUpload.controller;

import com.MyImageUpload.model.PageContent;
import com.MyImageUpload.service.PageContentService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/content")
//@CrossOrigin(origins = "*") // Allow Vue app to access API
public class PageContentController {

    @Autowired
    private PageContentService service;

    //This method created to get only TItle getCall
    @GetMapping("/news/{id}")
    public PageContent getTitleContent(@PathVariable Long id){
        return service.getContent(id)
                .orElseGet(()-> {
                    //Create default content if not found
                    PageContent newTitleContent = new PageContent();
                            newTitleContent.setHeadline("Breaking News");
                    return service.saveContent(newTitleContent);
                });
    }

    @GetMapping("/{id}")
    public PageContent getContent(@PathVariable Long id) {
        return service.getContent(id)
                .orElseGet(() -> {
                    // Create default content if not found
                    PageContent newContent = new PageContent();
                    newContent.setHeadline("Welcome to My Site!");
                    newContent.setParagraph1("This is the default first paragraph.");
                    newContent.setParagraph2("This is the default second paragraph.");
                    return service.saveContent(newContent);
                });
    }

    @PostMapping("/save")
    public PageContent saveContent(@RequestBody PageContent content) {
        return service.saveContent(content);
    }


}

