package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.IUserDao;
import com.vlogplusplus.entity.User;
import com.vlogplusplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao iLoginDao;

    @Override
    public User login(String username, String password) {
        return iLoginDao.login(username, password);
    }
}
