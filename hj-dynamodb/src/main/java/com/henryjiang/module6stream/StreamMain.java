package com.henryjiang.module6stream;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.streamsadapter.AmazonDynamoDBStreamsAdapterClient;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;

import java.util.UUID;

public class StreamMain {
    public static void main(String... args) {
        KinesisClientLibConfiguration workerConfig = createKCLConfiguration();
        var recordProcessorFactory = new StreamsRecordProcessorFactory();

        System.out.println("Creating worker");
        Worker worker = createKCLCWorker(workerConfig, recordProcessorFactory);

        System.out.println("Starting worker");
        worker.run();
    }

    private static KinesisClientLibConfiguration createKCLConfiguration() {
        String streamArn = "arn:aws:dynamodb:ap-northeast-1:397684039306:table/ShopItem/stream/2020-03-20T15:10:18.671";
        ProfileCredentialsProvider streamsCredentials = new ProfileCredentialsProvider();

        return new KinesisClientLibConfiguration(
                "items-table-stream-reader",
                streamArn,
                streamsCredentials,
                getWorkerId()
        )
                .withInitialPositionInStream(InitialPositionInStream.TRIM_HORIZON);
    }

    private static String getWorkerId() {
        return UUID.randomUUID().toString();
    }

    private static Worker createKCLCWorker(
            KinesisClientLibConfiguration workerConfig,
            StreamsRecordProcessorFactory recordProcessorFactory) {

        AmazonDynamoDBStreamsAdapterClient adapterClient
                = new AmazonDynamoDBStreamsAdapterClient();
        adapterClient.setRegion(
                Region.getRegion(Regions.AP_NORTHEAST_1));

        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.standard().build();

        return new Worker.Builder()
                .recordProcessorFactory(recordProcessorFactory)
                .config(workerConfig)
                .kinesisClient(adapterClient)
                .dynamoDBClient(dynamoDBClient)
                .build();
    }
}
