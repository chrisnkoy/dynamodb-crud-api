package com.christian.dynamodbcrudapi.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.christian.dynamodbcrudapi.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DynamoDbService {
    private final DynamoDBMapper dynamoDBMapper;

    public String saveUser(User user){
        dynamoDBMapper.save(user);
        return "User " + user.getUserId() + " has been saved.";
    }

    public User getUserById(String userId){
        return dynamoDBMapper.load(User.class, userId);
    }

    public List<User> getAllUsers(){
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(User.class, dynamoDBScanExpression);
    }

    public String updateUser(String userId, User user){
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression()
                .withExpectedEntry(
                        "userId",
                        new ExpectedAttributeValue(
                                new AttributeValue().withS(userId)
                ));
        dynamoDBMapper.save(user, dynamoDBSaveExpression);
        return "User " + userId + " has been updated.";
    }

    public String deleteUser(String userId){
        User user = dynamoDBMapper.load(User.class,  userId);
        dynamoDBMapper.delete(user);
        return "User " + userId + " was successfully deleted.";
    }
}
