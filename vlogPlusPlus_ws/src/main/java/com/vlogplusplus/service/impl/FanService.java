package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.IFanDao;
import com.vlogplusplus.entity.Fan;
import com.vlogplusplus.service.IFanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FanService implements IFanService {
    @Autowired
    private IFanDao iFanDao;


    @Override
    public List<Fan> list_fan(int up_id) {
        return iFanDao.list_fan(up_id);
    }

    @Override
    public List<Fan> list_fellow(int fan_id) {
        return iFanDao.list_fellow(fan_id);
    }

    @Override
    public void add_fan(int fan_id, int up_id) {
        iFanDao.add_fan(fan_id,up_id);
    }

    @Override
    public void del_fan(int fan_id, int up_id) {
        iFanDao.del_fan(fan_id,up_id);
    }
}
