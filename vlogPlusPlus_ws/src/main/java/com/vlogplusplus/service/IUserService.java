package com.vlogplusplus.service;

import com.vlogplusplus.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface IUserService {
    User login(String username, String password);
    User get_user(int u_id);
    void add(String username, String password, String nickname, String email,
             String phone, String image, byte sex,  Date birthday, String fashion);
}
