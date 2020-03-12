package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.IActivityDao;
import com.vlogplusplus.dao.IUserDao;
import com.vlogplusplus.entity.Activity;
import com.vlogplusplus.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService implements IActivityService {
    @Autowired
    private IActivityDao iActivityDao;

    @Override
    public List<Activity> list() {
        return iActivityDao.list();
    }
}
