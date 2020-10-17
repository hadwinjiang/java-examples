package com.henryjiang.module6stream;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.streamsadapter.model.RecordAdapter;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.IRecordProcessorCheckpointer;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.v2.IRecordProcessor;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.ShutdownReason;
import com.amazonaws.services.kinesis.clientlibrary.types.InitializationInput;
import com.amazonaws.services.kinesis.clientlibrary.types.ProcessRecordsInput;
import com.amazonaws.services.kinesis.clientlibrary.types.ShutdownInput;
import com.amazonaws.services.kinesis.model.Record;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

public class StreamRecordsProcessor implements IRecordProcessor {

    static private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void initialize(InitializationInput initializationInput) {

    }

    @Override
    public void processRecords(ProcessRecordsInput processRecordsInput) {
        for (Record record : processRecordsInput.getRecords()) {
            processRecord(record);

            checkpoint(processRecordsInput.getCheckpointer());
        }
    }

    private void processRecord(Record record) {
        if (record instanceof RecordAdapter) {
            com.amazonaws.services.dynamodbv2.model.Record streamRecord = ((RecordAdapter) record)
                    .getInternalObject();

            if ("INSERT".equals(streamRecord.getEventName())) {
                Map<String, AttributeValue> attributes
                        = streamRecord.getDynamodb().getNewImage();

                System.out.println(attributes);
                System.out.println(
                        "New item name: " + attributes.get("name").getS());

            }
        }
    }


    private void checkpoint(IRecordProcessorCheckpointer checkpointer) {
        try {
            checkpointer.checkpoint();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void shutdown(ShutdownInput shutdownInput) {
        ShutdownReason reason = shutdownInput.getShutdownReason();
        switch (reason) {
            case TERMINATE:
            case REQUESTED:
                checkpoint(shutdownInput.getCheckpointer());
                break;

            case ZOMBIE:
                System.out.println("Zombie shard. No checkpoint.");
                break;
        }
    }

}
