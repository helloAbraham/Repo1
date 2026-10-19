package com.MyImageUpload.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "page_content")
@Data
public class PageContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String headline;

    @Lob
    @Column(length = 2500)
    private String paragraph1;

    @Lob
    @Column(length = 2500)
    private String paragraph2;
}

