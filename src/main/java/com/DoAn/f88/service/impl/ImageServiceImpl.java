package com.DoAn.f88.service.impl;

import com.DoAn.f88.convert.ImageConvert;
import com.DoAn.f88.dto.ImageDTO;
import com.DoAn.f88.entity.AccountEntity;
import com.DoAn.f88.entity.ImageEntity;
import com.DoAn.f88.exeption.Error403.CheckNullVariable;
import com.DoAn.f88.exeption.Error403.ValidateException;
import com.DoAn.f88.exeption.Error403.ValidateValueForm;
import com.DoAn.f88.exeption.Error403.image.ValidateImage;
import com.DoAn.f88.repository.AccountRepository;
import com.DoAn.f88.repository.ImageRepository;
import com.DoAn.f88.request.ImageRequest;
import com.DoAn.f88.service.ImageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ImageConvert imageConvert;

    public ImageDTO addImages(ImageRequest imageRequest){
        Long accountId = CheckNullVariable.checkValidateLong(imageRequest.getAccountId());
        MultipartFile file = imageRequest.getFile();

        String fullName = file.getOriginalFilename();

        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> new ValidateException("Tài khoản không tồn tại"));
        String nameImg = fullName.substring(0,fullName.lastIndexOf(".")-1);
        String extension = fullName.substring(fullName.lastIndexOf(".") + 1);

        LocalDate localDate = LocalDate.now();

        ValidateImage.validateImage(extension, file);
        String newFileName = nameImg+"_"+accountEntity.getUsername()+"_"+localDate+"."+extension;

        try {
            InputStream inputStream = file.getInputStream();
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            File newFile = new File("src/main/resources/static/images/account/"+newFileName);
            OutputStream outputStream = new FileOutputStream(newFile);
            outputStream.write(bytes);

            inputStream.close();
            outputStream.close();

            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setUrl("/images/account/"+newFileName);
            imageRepository.save(imageEntity);
            accountEntity.setImageEntity(imageEntity);
            accountRepository.save(accountEntity);

            return imageConvert.toDTO(imageEntity);
        } catch (IOException e) {
            throw new ValidateException("Lỗi không thể tải lên ảnh");
        }
    }

    @Override
    public ImageDTO updateImages(ImageRequest imageRequest, String id, String imageId) {
        ValidateValueForm.validateNull(imageRequest);
        if (imageId.isEmpty()) {
            addImages(imageRequest);
        }else {
            Optional<ImageEntity> optImageEntity = imageRepository.findById(Long.valueOf(imageId));
            ImageEntity imageEntity = optImageEntity.get();

            Long accountId = CheckNullVariable.checkValidateLong(imageRequest.getAccountId());
            MultipartFile file = imageRequest.getFile();

            String fullName = file.getOriginalFilename();

            AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> new ValidateException("Tài khoản không tồn tại"));
            String nameImg = fullName.substring(0,fullName.lastIndexOf(".")-1);
            String extension = fullName.substring(fullName.lastIndexOf(".") + 1);

            LocalDate localDate = LocalDate.now();

            ValidateImage.validateImage(extension, file);
            String newFileName = nameImg+"_"+accountEntity.getUsername()+"_"+localDate+"."+extension;

            try {
                InputStream inputStream = file.getInputStream();
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                File newFile = new File("src/main/resources/static/images/account/"+newFileName);
                OutputStream outputStream = new FileOutputStream(newFile);
                outputStream.write(bytes);

                inputStream.close();
                outputStream.close();

                File oldfile = new File("src/main/resources/static/"+imageEntity.getUrl());
                oldfile.delete();

                imageEntity.setUrl("/images/account/"+newFileName);
                imageRepository.save(imageEntity);

                return imageConvert.toDTO(imageEntity);
            } catch (IOException e) {
                throw new ValidateException("Lỗi không thể tải lên ảnh");
            }
        }
        return null;
    }

    @Override
    public void deleteImages(String id) {
        CheckNullVariable.checkValidateLong(id);
        Long imageId = CheckNullVariable.checkValidateLong(id);
        ImageEntity imageEntity = imageRepository.findById(imageId).orElseThrow(()-> new ValidateException("Không tìm thấy ảnh"));
        File file = new File("src/main/resources/static/"+imageEntity.getUrl());
        file.delete();

        imageRepository.delete(imageEntity);
    }
}
