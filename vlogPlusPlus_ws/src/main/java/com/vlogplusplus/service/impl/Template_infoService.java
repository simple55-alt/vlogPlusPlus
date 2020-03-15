package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.ITemplate_infoDao;
import com.vlogplusplus.entity.Template_info;
import com.vlogplusplus.service.ITemplate_infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Template_infoService implements ITemplate_infoService {
    @Autowired
    private ITemplate_infoDao iTemplate_infoDao;

    @Override
    public List<Template_info> list() {
        return iTemplate_infoDao.list();
    }

    @Override
    public void add(String name, String summary, String detail, int u_id) {
        iTemplate_infoDao.add(name,summary,detail,u_id);
    }

    @Override
    public void update(String name, String summary, String detail, int u_id, int id) {
        iTemplate_infoDao.update(name,summary,detail,u_id,id);
    }

    @Override
    public void del(int id) {
        iTemplate_infoDao.del(id);
    }
}
