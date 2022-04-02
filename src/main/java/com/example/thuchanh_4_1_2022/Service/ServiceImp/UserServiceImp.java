package com.example.thuchanh_4_1_2022.Service.ServiceImp;

import com.example.thuchanh_4_1_2022.Domain.User;
import com.example.thuchanh_4_1_2022.Repository.UserRepository;
import com.example.thuchanh_4_1_2022.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User findById(String username, String password) {
        return userRepository.findById(username,password);
    }
}
