package com.ecom.ecom_application.controller;

import com.ecom.ecom_application.dto.UserDto;
import com.ecom.ecom_application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;



@RestController
public class UserController {

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
    public  ResponseEntity<Boolean> updateUser(@PathVariable Long Id,@RequestBody UserDto userDto){
        return  ResponseEntity.ok(userService.updateUser(Id,userDto));
    }




}
