package com.MyImageUpload.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Entity

public @Data class Image {

    @Id
    private String id;
    private String name;
    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    private Date currentDate;

}
