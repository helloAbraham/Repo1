package com.MyImageUpload.service;



import com.MyImageUpload.model.PageContent;
import java.util.Optional;

public interface PageContentService {
    Optional<PageContent> getContent(Long id);
    PageContent saveContent(PageContent content);
}

