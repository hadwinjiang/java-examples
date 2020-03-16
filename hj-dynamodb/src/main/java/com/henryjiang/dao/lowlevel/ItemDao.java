package com.henryjiang.dao.lowlevel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.henryjiang.domain.ShopItem;

import java.util.HashMap;
import java.util.Map;

public class ItemDao {

    private final AmazonDynamoDB dynamoDb;

    public ItemDao(AmazonDynamoDB dynamoDb) {
        this.dynamoDb = dynamoDb;
    }

    public void put(ShopItem shopItem) {
        Map<String, AttributeValue> itemMap = new HashMap<>();

        itemMap.put(
                "id",
                new AttributeValue().withS(shopItem.getId())
        );

        itemMap.put(
                "name",
                new AttributeValue().withS(shopItem.getName())
        );

        itemMap.put(
                "description",
                new AttributeValue().withS(shopItem.getDescription())
        );

        itemMap.put(
                "amount",
                new AttributeValue().withN(
                        Integer.toString(shopItem.getAmount())
                )
        );

        PutItemRequest putItemRequest = new PutItemRequest(
                "ShopItem",
                itemMap
        );

        dynamoDb.putItem(putItemRequest);

    }

    public ShopItem get(String id) {
        Map<String, AttributeValue> itemKey = new HashMap<>();
        itemKey.put(
                "id",
                new AttributeValue().withS(id)
        );

        GetItemRequest getItemRequest = new GetItemRequest()
                .withTableName("ShopItem")
                .withKey(itemKey)
                .withConsistentRead(true);

        GetItemResult getItemResult = dynamoDb.getItem(getItemRequest);
        Map<String, AttributeValue> dynamoDbItem = getItemResult.getItem();

        ShopItem resultItem = new ShopItem();

        resultItem.setId(
                dynamoDbItem.get("id").getS()
        );

        resultItem.setName(
                dynamoDbItem.get("name").getS()
        );

        resultItem.setDescription(
                dynamoDbItem.get("description").getS()
        );

        resultItem.setAmount(
                Integer.parseInt(
                        dynamoDbItem.get("amount").getN()
                )
        );

        return resultItem;
    }
}

