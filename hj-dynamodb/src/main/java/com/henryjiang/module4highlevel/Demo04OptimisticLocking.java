package com.henryjiang.module4highlevel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.henryjiang.dao.highlevel.ShopItemDao;
import com.henryjiang.domain.HighLevelShopItem;
import com.henryjiang.utility.Utils;

public class Demo04OptimisticLocking {
    public static void main(String... args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .build();

        Utils.createTable(HighLevelShopItem.class, client);
        optimisticLockingDemo(client);
    }

    private static void optimisticLockingDemo(AmazonDynamoDB dynamoDB) {
        ShopItemDao itemDao = new ShopItemDao(dynamoDB);

        HighLevelShopItem item = itemDao.put(new HighLevelShopItem("Computer", "Good one", 4));

        HighLevelShopItem item1 = itemDao.get(item.getId());
        HighLevelShopItem item2 = itemDao.get(item.getId());

        updateTitle(itemDao, item1);
        updateDescription(itemDao, item2);

        System.out.println(itemDao.get(item.getId()));
    }

    private static void updateTitle(ShopItemDao itemDao, HighLevelShopItem item) {
        System.out.println("Updating title");
        item.setName("Gaming laptop");
        itemDao.put(item);
        System.out.println("Title was updated");
    }

    private static void updateDescription(ShopItemDao itemDao, HighLevelShopItem item) {

//        System.out.println("Updating description");
//        item.setDescription("4K display");
//        itemDao.put(item);
//        System.out.println("Description was updated");

        while (true) {
            try {
                System.out.println("Updating description");
                item.setDescription("4K display");
                itemDao.put(item);
                System.out.println("Description was updated");
                break;
            } catch (ConditionalCheckFailedException ex) {
                System.out.println("Error was throw: ");
                System.out.println(ex.getMessage());
                item = itemDao.get(item.getId());
            }
        }

    }
}
