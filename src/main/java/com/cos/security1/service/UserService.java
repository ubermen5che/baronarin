package com.cos.security1.service;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface UserService {
    Boolean updateAuthStatus(String email);
}
