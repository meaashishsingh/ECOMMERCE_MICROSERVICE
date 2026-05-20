package com.ecommerce.user.service;


import com.ecommerce.user.dto.AddressDto;
import com.ecommerce.user.dto.UserDto;
import com.ecommerce.user.models.User;
import com.ecommerce.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    private ModelMapper modelMapper;
    public UserServiceImp(UserRepository userRepository,ModelMapper modelMapper){
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
    }
    public List<UserDto> getAllUser(){
   List<User> Users=userRepository.findAll();
//        return userRepository.findAll()
//                .stream()
//                .map(user -> modelMapper.map(user, UserDto.class))
//                .toList();



return Users.stream().map(user->new UserDto(user.getId(),user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), user.getRole(),modelMapper.map(user.getAddress(), AddressDto.class))).toList();
    }

    @Override
    public UserDto createUser(UserDto userDto) {
      User newUser=modelMapper.map(userDto,User.class);
     System.out.println("data  "+userDto.getFirstName()+" "+userDto.getLastName());
      User user=userRepository.save(newUser);
      return modelMapper.map(user,UserDto.class);
    }

    @Override
    public Boolean updateUser(Long id, UserDto updatedUser) {
        return userRepository.findById(id).filter(user->user.getId().equals(id)).map(existingUser->{
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            userRepository.save(existingUser);
            return true;
        }).orElse(false);

    }


}
