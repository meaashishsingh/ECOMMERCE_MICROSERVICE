package com.ecommerce.user.service;

import com.ecommerce.user.dto.AddressDTO;
import com.ecommerce.user.dto.UserDto;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.models.User;
import com.ecommerce.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserServiceImp(UserRepository userRepository,
                          ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    private UserResponse mapToUserResponse(User user){
        UserResponse response = new UserResponse();
//        response.setKeyCloakId(user.getKeycloakId());
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if (user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);
        }
        return response;
    }
    public Optional<UserResponse> fetchUser(String id) {
        return userRepository.findById(String.valueOf(id))
                .map(this::mapToUserResponse);
    }
//    @Override
//    public List<UserResponse> getAllUser() {
//
//        List<User> users = userRepository.findAll();
//
//        return users.stream()
//                .map(user -> new UserResponse(
//                        user.getId(),
//                        user.getFirstName(),
//                        user.getLastName(),
//                        user.getEmail(),
//                        user.getPhone(),
//                        user.getRole(),
//                        user.getAddress()!=null?modelMapper.map(user.getAddress(), AddressDTO.class):null
//                ))
//                .toList();
//    }
    @Override
    public List<UserResponse> getAllUser() {

        List<User> users = userRepository.findAll();

        System.out.println("Users found: " + users.size());

        return users.stream()
                .map(user -> {
                    System.out.println("User ID: " + user.getId());
                    System.out.println("Address: " + user.getAddress());

                    return new UserResponse(
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getEmail(),
                            user.getPhone(),
                            user.getRole(),
                            user.getAddress() != null
                                    ? modelMapper.map(user.getAddress(), AddressDTO.class)
                                    : null
                    );
                })
                .toList();
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        User newUser = modelMapper.map(userRequest, User.class);

        System.out.println(
                "data " + userRequest.getFirstName() + " " + userRequest.getLastName()
        );

        User savedUser = userRepository.save(newUser);

        return modelMapper.map(savedUser, UserResponse.class);
    }

    @Override
    public Boolean updateUser(String id, UserRequest updatedUser) {

        return userRepository.findById(id)
                .map(existingUser -> {

                    existingUser.setFirstName(updatedUser.getFirstName());

                    existingUser.setLastName(updatedUser.getLastName());

                    existingUser.setEmail(updatedUser.getEmail());

                    existingUser.setPhone(updatedUser.getPhone());

//                    existingUser.setRole(updatedUser.getRole());

                    userRepository.save(existingUser);

                    return true;
                })
                .orElse(false);
    }
}