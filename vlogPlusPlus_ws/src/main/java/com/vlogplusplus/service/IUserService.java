package com.vlogplusplus.service;

import com.vlogplusplus.entity.User;

public interface IUserService {
    User login(String username, String password);
}
