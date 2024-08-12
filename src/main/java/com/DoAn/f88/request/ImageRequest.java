package com.DoAn.f88.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageRequest {
    private String accountId;
    private MultipartFile file;
}
