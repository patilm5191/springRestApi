package com.neu.api.repository.impl;

import com.neu.api.entity.User;
import com.neu.api.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setEmail("abc@gmail.com");
        User user2 = new User();
        user2.setEmail("abc@gmail.com");
        User user3 = new User();
        user3.setEmail("abc@gmail.com");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return userList;
    }

    @Override
    public User findOne(String id) {
        User user1 = new User();
        user1.setEmail("abc@gmail.com");
        return user1;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(String id, User user) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
