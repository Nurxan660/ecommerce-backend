package com.example.ecommerce.service;

import com.example.ecommerce.dto.UpdateUserRequest;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.UserAlreadyExistException;
import com.example.ecommerce.exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommerce.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;




    public User update(Long id, UpdateUserRequest updateUserRequest) throws UserNotFound {
        Optional<User> optUser=userRepository.findById(id);
        User usr=optUser.orElse(null);
        if(usr==null){
            throw  new UserNotFound("User not found");
        }
        usr.setPassword(updateUserRequest.getPassword());
       return userRepository.save(usr);
    }
}
