package com.neu.api.service.impl;

import com.neu.api.entity.User;
import com.neu.api.exception.BadRequestException;
import com.neu.api.exception.NotFoundException;
import com.neu.api.repository.UserRepository;
import com.neu.api.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll( ) {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(String id) {
        User existing = repository.findOne(id);
        if(existing == null){
             throw new NotFoundException("User with id " + id + " does not exist");
        }
        return existing;
    }

    @Override
    @Transactional
    public User create(User user) {
        User existing = repository.findByEmail(user.getEmail());
        if(existing != null){
            throw new BadRequestException("User with email " + user.getEmail() + " already exist");
        }
        return repository.create(user);
    }

    @Override
    @Transactional
    public User update(String id, User user) {
        User existing = repository.findOne(id);
        if(existing == null){
            throw new NotFoundException("User with id " + id + " does not exist");
        }
        return repository.update(user);
    }

    @Override
    @Transactional
    public void delete(String id) {
        User existing = repository.findOne(id);
        if(existing == null){
            throw new NotFoundException("User with id " + id + " does not exist");
        }
        repository.delete(existing);
    }
}
