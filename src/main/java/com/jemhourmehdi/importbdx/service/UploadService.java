package com.jemhourmehdi.importbdx.service;

import com.jemhourmehdi.importbdx.payload.BordereauData;
import com.jemhourmehdi.importbdx.payload.BordereauPayload;
import com.jemhourmehdi.importbdx.storage.DynamoDBDataStorage;
import com.jemhourmehdi.importbdx.storage.S3FileStore;
import com.jemhourmehdi.importbdx.utils.CSVFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
            s3FileStore.upload(filename.concat(".csv"),csvBytes);
            dataStorage.save(data.getBordereauInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
