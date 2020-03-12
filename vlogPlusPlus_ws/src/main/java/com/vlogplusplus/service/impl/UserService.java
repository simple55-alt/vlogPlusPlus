package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.IUserDao;
import com.vlogplusplus.entity.User;
import com.vlogplusplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public void add(String username, String password, String nickname, String email, String phone, String image, byte sex, Date birthday, String fashion) {
        iUserDao.add(username, password, nickname, email, phone, image, sex, birthday, fashion);
    }

    @Override
    public void update_pass(String password, int u_id) {
        iUserDao.update_pass(password, u_id);
    }
}
