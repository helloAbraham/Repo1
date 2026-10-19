package com.MyImageUpload.model;

import lombok.Builder;
import lombok.Getter;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

@Builder
@Getter
public class Style {
    private boolean locked;
    private short fontSize;
    private boolean bold;
    private HorizontalAlignment alignment;

}
/**
    This Style class is where we define the properties for Excel styles.
    We use the @Builder annotation. So we can easily create instances of Style
    with different attributes
 */