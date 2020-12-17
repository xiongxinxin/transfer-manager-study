package com.xxx.transfermanagerstudy.controller;

import com.xxx.transfermanagerstudy.service.FileOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileOperationController {
    @Autowired
    private FileOperationService fileService;

    @RequestMapping("/uploadFile")
    public void uploadFile() {
        fileService.uploadFile();
    }

    @RequestMapping("/downloadFile")
    public void downloadFile() {
        fileService.downloadFile();
    }

}
