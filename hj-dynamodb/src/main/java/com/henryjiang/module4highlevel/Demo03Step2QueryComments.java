package com.henryjiang.module4highlevel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.henryjiang.dao.highlevel.CommentDao;

import static com.henryjiang.utility.Utils.pause;
import static com.henryjiang.utility.Utils.print;

public class Demo03Step2QueryComments {

    public static void main(String... args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .build();

        queryComments(client);
    }

    private static void queryComments(AmazonDynamoDB client) {
        CommentDao commentDao = new CommentDao(client);

        System.out.println("All comments with rating greater than 3: ");
        print(commentDao.allForItemWithMinRating("1", 3));

        pause();

        System.out.println();
        System.out.println("All comments by user with id 10: ");
        print(commentDao.allForUser("10"));

    }


}
