package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.IUserDao;
import com.vlogplusplus.entity.User;
import com.vlogplusplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao iUserDao;

    @Override
    public User login(String username, String password) {
        return iUserDao.login(username, password);
    }

    @Override
    public User get_user(int u_id) {
        return iUserDao.get_user(u_id);
    }
}
