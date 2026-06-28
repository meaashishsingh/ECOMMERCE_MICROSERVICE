package com.ecommerce.user.controller;


import com.ecommerce.user.dto.UserDto;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.models.User;
import com.ecommerce.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RefreshScope
@Slf4j
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Value("${build.version:default}")
    private  String buidversion;
    private final UserService userService;
  public UserController(UserService userService){
      this.userService=userService;
  }

    private List<UserDto> userList=new ArrayList<>();

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
//        logger.atInfo().log("Calling API to get all users");
//        logger.atDebug().log("Calling API to get all users");
//        logger.atTrace().log("Calling API to get all users");
//        logger.atError().log("Error while calling API to get all users");
        return ResponseEntity.ok(userService.getAllUser());
    }
    @PostMapping("/api/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
    }
    @PutMapping("/api/users/{id}")
    public  ResponseEntity<Boolean> updateUser(@PathVariable String Id,@RequestBody UserRequest userRequest){
        return  ResponseEntity.ok(userService.updateUser(Id,userRequest));
    }
    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id){
//        log.info("Request received for user: {}", id);
//
//        log.trace("This is TRACE level - Very detailed logs");
//        log.debug("This is DEBUG level - Used for development debugging");
//        log.info("This is INFO level - General system information");
//        log.warn("This is WARN level - Something might be wrong");
//        log.error("This is ERROR level - Something failed");

        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("api/users/temp")
    public String Meth(){
        return buidversion;
    }



}
