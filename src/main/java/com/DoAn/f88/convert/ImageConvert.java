package com.DoAn.f88.convert;

import com.DoAn.f88.dto.ImageDTO;
import com.DoAn.f88.entity.ImageEntity;
import com.DoAn.f88.request.ImageRequest;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ImageConvert {
    public ImageDTO toDTO(ImageEntity imageEntity) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(imageEntity.getId());
        imageDTO.setUrl(imageEntity.getUrl());
        return imageDTO;
    }
//    public ImageEntity toEntity(ImageRequest imageRequest, ImageEntity imageEntity) {
//
//         return imageEntity;
//    }
}
