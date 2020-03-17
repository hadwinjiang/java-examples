package com.henryjiang.module4highlevel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.henryjiang.dao.highlevel.CommentDao;
import com.henryjiang.domain.Comment;
import com.henryjiang.utility.Utils;


import java.time.LocalDateTime;

public class Demo03Step1PopulateComments {


    public static void main(String... args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .build();

        Utils.createTable(Comment.class, client);
        createComments(client);
    }

    private static void createComments(AmazonDynamoDB client) {
        System.out.println("Creating comments");
        CommentDao commentDao = new CommentDao(client);

        commentDao.put(new Comment(
                "1",
                "Delivered on time",
                "10",
                5,
                LocalDateTime.of(2019, 9, 15, 15, 53)
        ));

        commentDao.put(new Comment(
                "1",
                "Good stuff!",
                "11",
                4,
                LocalDateTime.of(2019, 10, 17, 10, 3)
        ));

        commentDao.put(new Comment(
                "1",
                "Not as described",
                "12",
                1,
                LocalDateTime.of(2019, 11, 3, 4, 11)
        ));

        commentDao.put(new Comment(
                "2",
                "So-so...",
                "10",
                3,
                LocalDateTime.of(2019, 11, 1, 19, 37)
        ));

        commentDao.put(new Comment(
                "3",
                "Packaging was damaged",
                "10",
                5,
                LocalDateTime.of(2019, 11, 10, 9, 15)
        ));

        System.out.println("Comments were created");
    }
}