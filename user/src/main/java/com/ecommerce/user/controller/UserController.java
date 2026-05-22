package com.ecommerce.user.controller;


import com.ecommerce.user.dto.UserDto;
import com.ecommerce.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RefreshScope
public class UserController {
    @Value("${build.version:default}")
    private  String buidversion;
    private final UserService userService;
  public UserController(UserService userService){
      this.userService=userService;
  }

    private List<UserDto> userList=new ArrayList<>();

    @GetMapping("/api/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }
    @PostMapping("/api/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }
    @PutMapping("/api/{id}")
    public  ResponseEntity<Boolean> updateUser(@PathVariable String Id,@RequestBody UserDto userDto){
        return  ResponseEntity.ok(userService.updateUser(Id,userDto));
    }
    @GetMapping("api/users/temp")
    public String Meth(){
        return buidversion;
    }



}
