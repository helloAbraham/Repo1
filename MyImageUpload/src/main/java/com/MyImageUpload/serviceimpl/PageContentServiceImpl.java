package com.MyImageUpload.serviceimpl;

import com.MyImageUpload.model.PageContent;
import com.MyImageUpload.service.PageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MyImageUpload.repository.PageContentRepository;
import java.util.Optional;

@Service
public class PageContentServiceImpl implements PageContentService {

    @Autowired
    private PageContentRepository repository;

    @Override
    public Optional<PageContent> getContent(Long id) {
        return repository.findById(id);
    }

    @Override
    public PageContent saveContent(PageContent content) {
        return repository.save(content);
    }
}

