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
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 上传文件
     */
    @Override
    public void uploadFile() {
        TransferManager manager = TransferManagerBuilder.standard().withS3Client(amazonS3).build();
        Upload upload = manager.upload(Constant.BUCKET_NAME, "kodexporer_stable.tar", new File("D:\\image\\各种镜像\\容器镜像\\500M 容器镜像\\kodexporer_stable.tar"));

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
        Download download = manager.download(Constant.BUCKET_NAME, "kodexporer_stable.tar", new File("/root/xiong/kodexporer_stable.tar"));
        try {
            download.waitForCompletion();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
