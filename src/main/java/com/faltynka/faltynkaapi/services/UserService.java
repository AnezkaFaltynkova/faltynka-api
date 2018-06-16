package com.faltynka.faltynkaapi.services;

import com.faltynka.faltynkaapi.model.User;
import com.faltynka.faltynkaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        User user = null;
        List<User> users = userRepository.findByEmail(email);
        if(users != null && users.size() > 0) {
            user = users.get(0);
        }
        return user;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
