package com.henryjiang.module3lowlevelapi;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.henryjiang.dao.lowlevel.ItemDao;
import com.henryjiang.domain.ShopItem;

public class PutGetDemo {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder
                .standard()
                .build();

        ItemDao itemDao = new ItemDao(client);

//        saveItem(itemDao);
        readItem(itemDao);
    }

    private static void readItem(ItemDao itemDao) {
        ShopItem item = itemDao.get("1");
        System.out.println(item);
    }

    private static void saveItem(ItemDao itemDao) {
        ShopItem shopItem = new ShopItem();
        shopItem.setId("1");
        shopItem.setName("Laptop");
        shopItem.setDescription("Powerful gaming laptop");
        shopItem.setAmount(5);

        itemDao.put(shopItem);
        System.out.println("Item was stored");
    }

}
