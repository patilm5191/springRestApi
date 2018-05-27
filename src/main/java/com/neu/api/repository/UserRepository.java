package com.neu.api.repository;

import com.neu.api.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    public List<User> findAll();
//    public User findOne(String id);
//    public User findByEmail(String email);

//    Use of Optional
    public Optional<User> findOne(String id);
    public Optional<User> findByEmail(String email);
    public User create(User user);
    public User update(User user);
    public void delete(User user);
}
