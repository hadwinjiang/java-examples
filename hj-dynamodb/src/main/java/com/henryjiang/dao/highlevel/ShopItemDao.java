package com.henryjiang.dao.highlevel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.henryjiang.domain.HighLevelShopItem;

import java.util.List;

public class ShopItemDao {
    private final DynamoDBMapper mapper;

    public ShopItemDao(AmazonDynamoDB dynamoDb) {
        this.mapper = new DynamoDBMapper(dynamoDb);
    }

    public HighLevelShopItem put(HighLevelShopItem item) {
        mapper.save(item);
        return item;
    }

    public HighLevelShopItem get(String id) {
        return mapper.load(HighLevelShopItem.class, id);
    }

    public void delete(String id) {
        HighLevelShopItem item = new HighLevelShopItem();
        item.setId(id);

        mapper.delete(item);
    }

    public List<HighLevelShopItem> getAll() {
        return mapper.scan(HighLevelShopItem.class, new DynamoDBScanExpression());
    }
}
