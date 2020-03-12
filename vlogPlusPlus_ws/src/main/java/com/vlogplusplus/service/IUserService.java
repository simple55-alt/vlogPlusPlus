package com.vlogplusplus.service;

import com.vlogplusplus.entity.User;

public interface IUserService {
    User login(String username, String password);
    User get_user(int u_id);
}
