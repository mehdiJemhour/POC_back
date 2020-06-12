package com.jemhourmehdi.importbdx.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class S3FileStore {

    @Value("${amazon.s3.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    public void upload(String filename, InputStream inputStream, ObjectMetadata metadata){
        amazonS3.putObject(bucketName,filename,inputStream,metadata);
    }
}
