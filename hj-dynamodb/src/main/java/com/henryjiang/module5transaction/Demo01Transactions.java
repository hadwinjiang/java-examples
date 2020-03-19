package com.henryjiang.module5transaction;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import com.henryjiang.dao.highlevel.ShopItemDao;
import com.henryjiang.domain.HighLevelShopItem;
import com.henryjiang.domain.Order;
import com.henryjiang.utility.Utils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.henryjiang.utility.Utils.createTable;

public class Demo01Transactions {

    public static void main(String[] args) {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .build();

        // Second round comment out below
        createTable(Order.class, client);
        createTable(HighLevelShopItem.class, client);

        ShopItemDao itemDao = new ShopItemDao(client);
        HighLevelShopItem item = itemDao.put(new HighLevelShopItem("Computer", "Good one", 10));

        String itemId = item.getId();
//        String itemId = "94f2b406-244d-446a-954d-b89a5a7ae4fd";
        int purchaseAmount = 6;

        Update updateItem = new Update()
                .withTableName("highlevel-shopitem")
                .withKey(Map.of(
                        "id", new AttributeValue().withS(itemId)
                ))
                .withConditionExpression("amount > :purchaseAmount")
                .withUpdateExpression("SET amount = amount - :purchaseAmount, version = version + :versionIncrement")
                .withReturnValuesOnConditionCheckFailure("ALL_OLD")
                .withExpressionAttributeValues(Map.of(
                        ":purchaseAmount", new AttributeValue().withN("6"),
                        ":versionIncrement", new AttributeValue().withN("1")
                ));

        Put newOrder = new Put()
                .withTableName("highlevel-order")
                .withItem(Map.of(
                        "id", new AttributeValue(UUID.randomUUID().toString()),
                        "itemId", new AttributeValue(itemId),
                        "amount", new AttributeValue().withN(Integer.toString(purchaseAmount))
                ));

        List<TransactWriteItem> operations = List.of(
                new TransactWriteItem().withUpdate(updateItem),
                new TransactWriteItem().withPut(newOrder)
        );

        TransactWriteItemsRequest writeItemsRequest = new TransactWriteItemsRequest()
                .withTransactItems(operations);

        try {
            client.transactWriteItems(writeItemsRequest);
        } catch (TransactionCanceledException e) {
            System.out.println(e.getMessage());
            System.out.println("Cancellation reason" + e.getCancellationReasons().get(0).getItem());
        }
    }

}
