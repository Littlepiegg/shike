package com.example.shike.service;

import com.example.shike.entity.User;
import com.example.shike.mapper.UserRepository;
import com.example.shike.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(String phone, String password) {
        if (userRepository.findByPhone(phone).isPresent()) {
            throw new IllegalArgumentException("手机号已注册");
        }

        User user = new User();
        user.setPhone(phone);
        user.setPassword(MD5Util.encrypt(password));
        user.setStuNumber(UUID.randomUUID().toString().substring(0, 8)); // 简单生成学号
        return userRepository.save(user);
    }

    public User login(String account, String password) {
        Optional<User> userOpt = userRepository.findByPhone(account);
        if (userOpt.isEmpty()) {
            userOpt = userRepository.findByStuNumber(account);
        }

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(MD5Util.encrypt(password))) {
            return userOpt.get();
        } else {
            throw new IllegalArgumentException("账号或密码错误");
        }
    }
}
