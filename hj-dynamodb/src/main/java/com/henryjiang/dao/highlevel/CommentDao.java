package com.henryjiang.dao.highlevel;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.henryjiang.domain.Comment;

import java.util.List;
import java.util.Map;

public class CommentDao {

    private final DynamoDBMapper mapper;

    public CommentDao(AmazonDynamoDB dynamoDB) {
        this.mapper = new DynamoDBMapper(dynamoDB);
    }

    public Comment put(Comment comment) {
        mapper.save(comment);
        return comment;
    }

    public List<Comment> getAll() {
        return mapper.scan(Comment.class, new DynamoDBScanExpression());
    }


    public List<Comment> allForItemWithMinRating(String itemId, int minRating) {
        Comment comment = new Comment();
        comment.setItemId(itemId);

        Condition condition = new Condition()
                .withComparisonOperator(ComparisonOperator.GE)
                .withAttributeValueList(
                        new AttributeValue()
                                .withN(Integer.toString(minRating)));

        // Use LSI
        DynamoDBQueryExpression<Comment> queryExpression
                = new DynamoDBQueryExpression<Comment>()
                .withHashKeyValues(comment)
                .withRangeKeyCondition(
                        "rating",
                        condition
                )
                .withScanIndexForward(false);

        return mapper.query(Comment.class, queryExpression);
    }

    public List<Comment> allForUser(String userId) {
        Comment comment = new Comment();
        comment.setUserId(userId);

        // Use GSI
        DynamoDBQueryExpression<Comment> queryExpression
                = new DynamoDBQueryExpression<Comment>()
                .withHashKeyValues(comment)
                .withConsistentRead(false)
                .withScanIndexForward(false);

        return mapper.query(Comment.class, queryExpression);
    }
}
