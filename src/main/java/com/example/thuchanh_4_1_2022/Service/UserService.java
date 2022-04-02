package com.example.thuchanh_4_1_2022.Service;

import com.example.thuchanh_4_1_2022.Domain.Product;
import com.example.thuchanh_4_1_2022.Domain.User;

public interface UserService {
    public User findById(String username, String password);
}
