package com.DoAn.f88.api.user;

import com.DoAn.f88.dto.ImageDTO;
import com.DoAn.f88.request.ImageRequest;
import com.DoAn.f88.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/user/api/v1/images")
public class ImageApi {
    @Autowired
    private ImageService  imageService;

    @PostMapping(path = "/addImages",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ImageDTO addImages(@RequestParam("id") String id,
                              @RequestPart(required = false) MultipartFile file){
        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setAccountId(id);
        imageRequest.setFile(file);
        return imageService.addImages(imageRequest);
    }

    @PutMapping(path = "/updateImages",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ImageDTO putImages(@RequestParam("id") String id,
                              @RequestParam("imageId") String imageId,
                              @RequestPart(required = false) MultipartFile file){
        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setAccountId(id);
        imageRequest.setFile(file);
        return imageService.updateImages(imageRequest,id, imageId);
    }
}
