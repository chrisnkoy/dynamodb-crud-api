package com.christian.dynamodbcrudapi.controller;

import com.christian.dynamodbcrudapi.entity.User;
import com.christian.dynamodbcrudapi.service.DynamoDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class DynamoDbController {

    DynamoDbService dynamoDbService;
    @GetMapping("/")
    public String greeting(){
        return "Hello from API v1";
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return dynamoDbService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") String userId){
        return dynamoDbService.getUserById(userId);
    }

    @PostMapping("/users")
    public String saveUser(@RequestBody User user){
        dynamoDbService.saveUser((user));
        return "User has been saved successfully!";
    }
}
