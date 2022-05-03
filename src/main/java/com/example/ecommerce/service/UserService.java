package com.example.ecommerce.service;

import com.example.ecommerce.dto.ChangeAddressRequest;
import com.example.ecommerce.dto.UpdateUserRequest;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.entity.UserAddress;
import com.example.ecommerce.exception.AddressNotFoundException;
import com.example.ecommerce.exception.UserAlreadyExistException;
import com.example.ecommerce.exception.UserNotFound;
import com.example.ecommerce.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommerce.repository.UserRepository;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;




    public User update(Long id, UpdateUserRequest updateUserRequest) throws UserNotFound {
        Optional<User> optUser=userRepository.findById(id);
        User usr=optUser.orElse(null);
        if(usr==null){
            throw  new UserNotFound("User not found");
        }
        usr.setPassword(updateUserRequest.getPassword());
       return userRepository.save(usr);
    }

    public UserAddress getUserAddress(Long userId){
        UserAddress userAddress=userAddressRepository.findByUserId(userId).orElseThrow(()->new AddressNotFoundException("address not found"));
        return userAddress;
    }
    public void changeUserAddress(ChangeAddressRequest req,Long userId){
        UserAddress userAddress=userAddressRepository.findByUserId(userId).orElseThrow(()->new AddressNotFoundException("address not found"));
        userAddress.setStreet(req.getStreet());
        userAddress.setHomeNumber(req.getHomeNumber());
        userAddress.setApartment(req.getApartment());
        userAddress.setFloor(req.getFloor());
        userAddressRepository.save(userAddress);
    }

}
