package com.devkduck.user.mapper;

import com.devkduck.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;


@Mapper
public interface UserMapper {
    Optional<User> findByEmail(String email);
    int save(User user);
}
