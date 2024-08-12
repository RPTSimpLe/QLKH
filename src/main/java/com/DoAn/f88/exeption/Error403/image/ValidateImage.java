package com.DoAn.f88.exeption.Error403.image;

import com.DoAn.f88.exeption.Error403.ValidateException;
import org.springframework.web.multipart.MultipartFile;

public class ValidateImage {
    public static void validateImage(String extension, MultipartFile file) {
        Integer count = 0;
        String []exts = {"jpg","png","jepg"};
        for(String ext : exts){
            if (extension.equals(ext)){
                count ++;
            }
        }

        if(count == 0){
            throw new ValidateException("Vui lòng tải ảnh jpg,png,jepg");
        }

        Long size = file.getSize();
        if (size > 3 * 1024 * 1024) { // 3MB in bytes
            throw new ValidateException("Kích thước tệp không được vượt quá 3MB");
        }
    }
}
