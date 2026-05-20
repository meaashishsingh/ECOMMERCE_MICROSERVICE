package com.ecom.ecom_application.service;

import com.ecom.ecom_application.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    List<UserDto> getAllUser();

    UserDto createUser(UserDto userDto);

    Boolean updateUser(Long id, UserDto userDto);
}
