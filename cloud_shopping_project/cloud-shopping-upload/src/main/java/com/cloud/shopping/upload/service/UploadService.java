package com.cloud.shopping.upload.service;

import com.cloud.shopping.common.enums.ExceptionEnum;
import com.cloud.shopping.common.exception.CloudException;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.cloud.shopping.upload.config.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private UploadProperties prop;
    //private static final List<String> ALLOW_TYPES = Arrays.asList("image/jpeg","image/png","image/bmp");
    public String uploadImage(MultipartFile file) {
        try {
            //Verify the file type
            String contentType = file.getContentType();
            if(!prop.getAllowTypes().contains(contentType)){
                throw new CloudException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //Verify the file type
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image == null){
                throw new CloudException(ExceptionEnum.INVALID_FILE_TYPE);
            }

            //Upload to FastDFS
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadFile(
                    file.getInputStream(), file.getSize(), extension, null);
            //Return path
            return prop.getBaseUrl()+storePath.getFullPath();
        } catch (IOException e) {
            log.error("[文件上传] 上传文件失败!",e);
            throw new CloudException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }

    }
}