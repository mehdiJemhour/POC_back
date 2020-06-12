package com.jemhourmehdi.importbdx.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.jemhourmehdi.importbdx.utils.CSVFactory;
import com.jemhourmehdi.importbdx.payload.BordereauData;
import com.jemhourmehdi.importbdx.payload.BordereauPayload;
import com.jemhourmehdi.importbdx.storage.DynamoDBDataStorage;
import com.jemhourmehdi.importbdx.storage.S3FileStore;
import com.jemhourmehdi.importbdx.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final CSVFactory csvFactory;
    private final S3FileStore s3FileStore;
    private final DynamoDBDataStorage dataStorage;

    public void upload(BordereauPayload data, String filename) {
        BordereauData bordereauData = data.getBordereauData();
        try {
            byte[] csvBytes = csvFactory.createCSV(bordereauData);
            InputStream inputStream = Utils.toInputStream(csvBytes);
            ObjectMetadata metaData = new ObjectMetadata();
            metaData.setContentLength(csvBytes.length);
            s3FileStore.upload(filename.concat(".csv"),inputStream,metaData);
            dataStorage.save(data.getBordereauInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
