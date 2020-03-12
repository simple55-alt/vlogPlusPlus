package com.vlogplusplus.service;

import com.vlogplusplus.entity.User;

import java.util.Date;

public interface IUserService {
    User login(String username, String password);
    User get_user(int u_id);
    void add(String username, String password, String nickname, String email,
             String phone, String image, byte sex,  Date birthday, String fashion);
    void update_pass(String password,int u_id);
    void del(int u_id);

}
