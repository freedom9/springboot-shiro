package com.freedom.service;

import com.freedom.entity.User;

/**
 * @Auther: freedom
 * @Date: 2018/10/30
 * @Description:
 */
public interface UserService {
    public User findByUsername(String username);

    public User findById(Integer id);
}
