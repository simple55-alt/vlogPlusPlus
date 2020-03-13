package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.IActivityDao;
import com.vlogplusplus.entity.Activity;
import com.vlogplusplus.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActivityService implements IActivityService {
    @Autowired
    private IActivityDao iActivityDao;

    @Override
    public List<Activity> list() {
        return iActivityDao.list();
    }

    @Override
    public void add(String title, String type, String var, Date begin_time, Date end_time, String method, String image) {
        iActivityDao.add(title,type,var,begin_time,end_time,method,image);
    }

    @Override
    public List<Activity> list_new(int n) {
        return iActivityDao.list_new(n);
    }

    @Override
    public void update(String title, String type, String var, Date begin_time, Date end_time, String method, String image, int id) {
        iActivityDao.update(title,type,var,begin_time,end_time,method,image,id);
    }

    @Override
    public void del(int id) {
        iActivityDao.del(id);
    }


}
