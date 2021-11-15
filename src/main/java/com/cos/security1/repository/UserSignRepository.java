package com.cos.security1.repository;

import com.cos.security1.domain.UserSign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSignRepository extends JpaRepository<UserSign, String> {

    UserSign save(UserSign userSign);
    UserSign findByFileName(String fileName);
}
