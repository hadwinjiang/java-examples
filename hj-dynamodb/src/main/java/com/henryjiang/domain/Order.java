package com.henryjiang.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "highlevel-order")
public class Order {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    private String itemId;

    @DynamoDBAttribute
    private int amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
