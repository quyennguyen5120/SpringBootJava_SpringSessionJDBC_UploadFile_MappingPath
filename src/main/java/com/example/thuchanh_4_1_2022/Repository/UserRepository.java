package com.example.thuchanh_4_1_2022.Repository;

import com.example.thuchanh_4_1_2022.Domain.Product;
import com.example.thuchanh_4_1_2022.Domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User findById(String username, String Password){
        User p = User.Users.stream().filter(x->x.getUsername().equals(username ) && x.getPassword().equals(Password)).findAny().orElse(null);
        return p;
    }
}
