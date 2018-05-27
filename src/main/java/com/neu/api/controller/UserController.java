package com.neu.api.controller;

import com.neu.api.constants.URI;
import com.neu.api.entity.User;

import com.neu.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "users")
public class UserController {
    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Find All Users", notes = "Returns a list of users in the app")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<User> findAll(){
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = URI.ID)
    @ApiOperation(value = "Find User by Id", notes = "Returns a user by Id if it exists in the app")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public User findOne(@PathVariable("id") String userId){
        return service.findOne(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create User", notes = "Create a User in the app with unique email")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public User create(@RequestBody User user){
        return service.create(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = URI.ID)
    @ApiOperation(value = "Update User", notes = "Updates an existing user")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public User update(@PathVariable("id") String id,  @RequestBody User user){
        return service.update(id, user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = URI.ID)
    @ApiOperation(value = "Delete User", notes = "Delete and existing user")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void delete(@PathVariable("id") String id){
        service.delete(id);
    }
}
