package com.ecommerce.user.service;

import com.ecommerce.user.dto.UserDto;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<UserResponse> getAllUser();

    UserResponse createUser(UserRequest userRequest);

    Boolean updateUser(String id,UserRequest userRequest);

    Optional<UserResponse> fetchUser(String id);
}
