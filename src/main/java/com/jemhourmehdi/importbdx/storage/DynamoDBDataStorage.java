package com.jemhourmehdi.importbdx.storage;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.jemhourmehdi.importbdx.payload.BordereauInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DynamoDBDataStorage {

    private final AmazonDynamoDB dynamoDB;
    @Value("${amazon.dynamoDB.name}")
    private String tableName;

    public void save(BordereauInfo bordereauInfo){
        Map<String, AttributeValue> mappingValues = new HashMap<>();
        mappingValues.put("id", new AttributeValue().withS(UUID.randomUUID().toString()));
        mappingValues.put("mga", new AttributeValue().withS(bordereauInfo.getMga()));
        mappingValues.put("paAgreement", new AttributeValue().withS(bordereauInfo.getPaAgreement()));
        mappingValues.put("program", new AttributeValue().withS(bordereauInfo.getProgram()));
        mappingValues.put("receivedDate", new AttributeValue().withS(bordereauInfo.getReceivedDate()));
        mappingValues.put("reportingMonth", new AttributeValue().withS(bordereauInfo.getReportingMonth()));
        mappingValues.put("reportingYear", new AttributeValue().withS(bordereauInfo.getReportingYear()));
        PutItemRequest putItemRequest = new PutItemRequest()
                .withTableName(tableName)
                .withItem(mappingValues);
        dynamoDB.putItem(putItemRequest);
    }

}
