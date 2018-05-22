package com.neu.api.repository;

import com.neu.api.entity.User;

import java.util.List;

public interface UserRepository {

    public List<User> findAll();
    public User findOne(String id);
    public User findByEmail(String email);
    public User create(User user);
    public User update(String id, User user);
    public void delete(String id);
}
