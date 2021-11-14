package com.cos.security1.service.impl;

import com.cos.security1.domain.User;
import com.cos.security1.domain.UserSign;
import com.cos.security1.repository.UserRepository;
import com.cos.security1.repository.UserSignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements com.cos.security1.service.UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSignRepository userSignRepository;

    @Override
    public Boolean updateAuthStatus(String email) {
        System.out.println("updateAuthStatus email = " + email);
        System.out.println("email.getClass() = " + email.getClass());
        User user = userRepository.findByEmail("serepa03@gmail.com");

        System.out.println("user = " + user);

        if (user == null)
            return false;
        else {
            user.setAuthStatus("Y");
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public User findUser(String email) {
        User user = userRepository.findByEmail(email);

        return user;
    }
    @Override
    public void saveUserSign(UserSign userSign) {
        userSignRepository.save(userSign);
    }
}
