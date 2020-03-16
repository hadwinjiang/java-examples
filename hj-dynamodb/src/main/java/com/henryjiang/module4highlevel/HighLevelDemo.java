package com.henryjiang.module4highlevel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.henryjiang.dao.highlevel.ShopItemDao;
import com.henryjiang.domain.HighLevelShopItem;

import static com.henryjiang.utility.Utils.*;

public class HighLevelDemo {
    public static void main(String[] args) {

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .build();

        createTable(HighLevelShopItem.class, client);
        highLevelDemo(client);

    }

    private static void highLevelDemo(AmazonDynamoDB client) {
        ShopItemDao itemDao = new ShopItemDao(client);

        HighLevelShopItem item1 = itemDao.put(
                new HighLevelShopItem(
                        "Lawn mower",
                        "The very best",
                        4));

        HighLevelShopItem item2 = itemDao.put(
                new HighLevelShopItem(
                        "Smart TV",
                        "New model",
                        5));
        HighLevelShopItem item3 = itemDao.put(
                new HighLevelShopItem(
                        "Laptop",
                        "Powerful gaming laptop",
                        10));

        System.out.println("All items:");
        print(itemDao.getAll());
        pause();

        System.out.println();
        System.out.println("After deletion");
        itemDao.delete(item2.getId());

        print(itemDao.getAll());
    }
}
