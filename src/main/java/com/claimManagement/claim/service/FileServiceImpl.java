package com.claimManagement.claim.service;


import com.claimManagement.claim.exception.FileStorageException;
import com.claimManagement.claim.exception.MyFileNotFoundException;
import com.claimManagement.claim.model.Claim;
import com.claimManagement.claim.model.FileEntity;
import com.claimManagement.claim.repository.ClaimRepository;
import com.claimManagement.claim.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ClaimRepository claimManagementRepository;

    @Override
    public FileEntity storeFile(MultipartFile file, Long id) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {

            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Claim claim = claimManagementRepository.findById(id).
                    orElseThrow(() -> new MyFileNotFoundException("File not found with id " + id));

            String UPLOAD_DIR = "C:\\Users\\Likhil\\Downloads\\claim\\";
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            FileEntity fileEntity = new FileEntity(fileName, file.getContentType(),file.getOriginalFilename(), claim);

            return fileRepository.save(fileEntity);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public List<FileEntity> getFile(Long claimId) {
        return fileRepository.findByClaimId(claimId);
    }
}
