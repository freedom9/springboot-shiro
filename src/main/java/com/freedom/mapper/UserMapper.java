package com.freedom.mapper;

import com.freedom.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Auther: freedom
 * @Date: 2018/10/30
 * @Description:
 */
@Mapper
public interface UserMapper {
    public User findByUsername(String username);

    public User findById(Integer id);
}
