package com.freedom.service.impl;

import com.freedom.entity.User;
import com.freedom.mapper.UserMapper;
import com.freedom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: freedom
 * @Date: 2018/10/30
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

}
