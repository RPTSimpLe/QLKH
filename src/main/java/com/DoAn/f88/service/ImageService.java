package com.DoAn.f88.service;

import com.DoAn.f88.dto.ImageDTO;
import com.DoAn.f88.request.ImageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ImageService {
    ImageDTO addImages(ImageRequest imageRequest);
    ImageDTO updateImages(ImageRequest imageRequest, String id, String imageId);
    void deleteImages(String id);
}
