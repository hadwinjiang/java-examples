package com.henryjiang.module4highlevel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.henryjiang.dao.highlevel.SupportMessageDao;

import static com.henryjiang.utility.Utils.pause;
import static com.henryjiang.utility.Utils.print;

public class Demo02Step2QuerySupportMessages {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .build();

        querySupportMessages(client);
    }

    private static void querySupportMessages(AmazonDynamoDB client) {
        SupportMessageDao messageDao = new SupportMessageDao(client);

        System.out.println("All messages for the payments department");
        print(messageDao.getAllForDepartment("payments"));

        pause();

        System.out.println();
        System.out.println("All messages for the payments department on a specific date");
        print(messageDao.getAllForDepartmentOnDay("payments", "2019-09-15"));

        pause();

        System.out.println();
        System.out.println("All urgent messages");
        print(messageDao.getAllUrgentMessages("payments"));
    }
}
