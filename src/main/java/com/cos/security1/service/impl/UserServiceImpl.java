package com.cos.security1.service.impl;

import com.cos.security1.domain.User;
import com.cos.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements com.cos.security1.service.UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean updateAuthStatus(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null)
            return false;
        else {
            user.setAuthStatus("Y");
            userRepository.save(user);
            return true;
        }
    }
}
