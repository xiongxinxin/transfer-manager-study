package com.xxx.transfermanagerstudy.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.xxx.transfermanagerstudy.Constant;
import com.xxx.transfermanagerstudy.service.FileOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 分片上传、分片下载
 *
 * @author xiong.xinxin
 */

@Service
public class FileOperationServiceImpl implements FileOperationService {
    @Autowired
    private AmazonS3 amazonS3;

    private static final String OBJECT_KEY = "nginxtest.tar";

    private static final String SOURCE_FILE = "/root/source/" + OBJECT_KEY;
    private static final String TARGET_FILE = "C:\\Users\\HP\\Desktop\\" + OBJECT_KEY;

    /**
     * 上传文件
     */
    @Override
    public void uploadFile() {
        TransferManager manager = TransferManagerBuilder.standard().withS3Client(amazonS3).build();
        Upload upload = manager.upload(Constant.BUCKET_NAME, OBJECT_KEY, new File(SOURCE_FILE));

        try {
            upload.waitForUploadResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     */
    @Override
    public void downloadFile() {
        TransferManager manager = TransferManagerBuilder.standard().withS3Client(amazonS3).build();
        Download download = manager.download(Constant.BUCKET_NAME, OBJECT_KEY, new File(TARGET_FILE));
        try {
            download.waitForCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
