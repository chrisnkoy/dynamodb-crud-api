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
    public String postUser(@RequestBody User user){
        return dynamoDbService.saveUser((user));
    }

    @PutMapping("/users/{id}")
    public String  updateUser( @PathVariable("id") String userId, @RequestBody User user){
        return dynamoDbService.updateUser(userId, user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") String userId){
        return dynamoDbService.deleteUser(userId);
    }
}
