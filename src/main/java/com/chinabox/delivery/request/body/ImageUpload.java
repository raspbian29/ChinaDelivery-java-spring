package com.chinabox.delivery.request.body;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class ImageUpload {
    private Long packageRequestId;
    private List<String> images;
}
