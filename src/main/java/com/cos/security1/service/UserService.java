package com.cos.security1.service;

import com.cos.security1.domain.User;
import com.cos.security1.domain.UserSign;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface UserService {
    Boolean updateAuthStatus(String email);
    User findUser(String email);
    void saveUser(User user);
    void saveUserSign(UserSign userSign);
}
