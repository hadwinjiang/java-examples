package com.henryjiang.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

@DynamoDBTable(tableName = "highlevel-shopitem")
public class HighLevelShopItem {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    private String name;

    private String description;

    private int amount;

    // Version to achieve optimistic locking
    @DynamoDBVersionAttribute
    private Long version;

    public HighLevelShopItem() {

    }

    public HighLevelShopItem(String name, String description, int amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}