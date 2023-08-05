package com.christian.dynamodbcrudapi.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.christian.dynamodbcrudapi.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DynamoDbService {
    private final DynamoDBMapper dynamoDBMapper;

    public User saveUser(User user){
        dynamoDBMapper.save(user);
        return user;
    }

    public User getUserById(String userId){
        return dynamoDBMapper.load(User.class, userId);
    }

    public List<User> getAllUsers(){
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(User.class, dynamoDBScanExpression);
    }
}
