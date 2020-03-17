package com.henryjiang.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.henryjiang.utility.LocalDateTimeConverter;


import java.time.LocalDateTime;

@DynamoDBTable(tableName = "highlevel-comment")
public class Comment {
    @DynamoDBHashKey
    private String itemId;

    @DynamoDBRangeKey
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    @DynamoDBIndexRangeKey(globalSecondaryIndexName = "UserID-index")   // GSI
    private LocalDateTime time;

    private String message;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "UserID-index")    // GSI
    private String userId;

    @DynamoDBIndexRangeKey(localSecondaryIndexName = "Rating-index")    // LSI
    private int rating;

    public Comment() {

    }

    public Comment(String itemId, String msg, String userId, int rating, LocalDateTime time) {
        this.itemId = itemId;
        this.message = msg;
        this.userId = userId;
        this.rating = rating;
        this.time = time;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "itemId='" + itemId + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", userId='" + userId + '\'' +
                ", rating=" + rating +
                '}';
    }
}
