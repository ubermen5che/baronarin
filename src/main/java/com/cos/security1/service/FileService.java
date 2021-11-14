package com.cos.security1.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface FileService {
    String fileUpload(MultipartFile multipartFile, String user_sign_name);

    void fileCustomerCenterUpload(MultipartFile multipartFile, String user_sign_name);

    Resource loadFile(String server_fileName) throws FileNotFoundException;
    Resource loadFile(String subPath, String server_fileName) throws FileNotFoundException;

    void fileBoardUpload(MultipartFile multipartFile, String user_sign_name);

    String getUpBoard();

    void setUpBoard(String upBoard);

    String getUpCustomerCenter();

    void setUpCustomerCenter(String upCustomerCenter);

    void setUploadDir(String uploadDir);

    String getUpResultDir();

    void setUpResultDir(String upResultDir);

    String getUploadDir();

    String getUpDownloadDir();

    void setUpDownloadDir(String upDownloadDir);
}
