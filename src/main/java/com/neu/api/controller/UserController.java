package com.neu.api.controller;

import com.neu.api.constants.URI;
import com.neu.api.entity.User;

import com.neu.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    @Controller
    @ResponseBody                           //if we apply for class level, then it will automatically apply to method level.
    MediaType is not required now its optional.
*/
/**
 *  Example of requestMapping with MediaType even though it is not required
 *  @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
 **/

@RestController                             // This replaces both @Controller and @ResponseBody annotations
@RequestMapping(value = URI.USERS )           // same as @ResponseBody
public class UserController {
    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll(){
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = URI.ID)
    public User findOne(@PathVariable("id") String userId){
        return service.findOne(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return service.create(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = URI.ID)
    public User update(@PathVariable("id") String id,  @RequestBody User user){
        return service.update(id, user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = URI.ID)
    public void delete(@PathVariable("id") String id){
        service.delete(id);
    }
}
