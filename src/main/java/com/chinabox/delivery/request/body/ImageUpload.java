package com.chinabox.delivery.request.body;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
public class ImageUpload {
    @JsonDeserialize
    private Long packageRequestId;
    @JsonDeserialize
    private List<String> images;
}
