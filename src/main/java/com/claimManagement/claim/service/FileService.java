package com.claimManagement.claim.service;

import com.claimManagement.claim.model.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileEntity storeFile(MultipartFile file, Long id);
    List<FileEntity> getFile(Long fileId);
}