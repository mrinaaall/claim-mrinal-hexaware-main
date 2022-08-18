package com.claimManagement.claim.contoller;

import com.claimManagement.claim.fileDto.UploadFileResponse;
import com.claimManagement.claim.model.FileEntity;
import com.claimManagement.claim.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping ("/uploadFile/{id}")
    public UploadFileResponse uploadFile(@PathVariable(value = "id") Long id, @RequestParam("file") MultipartFile file) {

        FileEntity fileEntity = fileService.storeFile(file, id);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileEntity.getFileId())
                .toUriString();

        return new UploadFileResponse(fileEntity.getName(), fileDownloadUri,
                file.getContentType(),fileEntity.getDIR_location());
    }



    @GetMapping("/getFile/{claimId}")
    public List<UploadFileResponse> downloadFile(@PathVariable Long claimId) {
        // Load file from database
        List<FileEntity> fileEntity = fileService.getFile(claimId);

        return fileEntity.stream().map(file -> new UploadFileResponse(file.getName(),
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
                        .path(file.getFileId()).toUriString(), file.getType(), "local")).toList();
    }
}
