package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.IVisitorDao;
import com.vlogplusplus.entity.Visitor;
import com.vlogplusplus.service.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService implements IVisitorService {
    @Autowired
    private IVisitorDao iVisitorDao;

    @Override
    public void add(int u_id, int up_id) {
        iVisitorDao.add(u_id,up_id);
    }

    @Override
    public List<Visitor> listForVisitor(int u_id, int n) {
        return iVisitorDao.listForVisitor(u_id,n);
    }

    @Override
    public List<Visitor> listForUp(int up_id, int n) {
        return iVisitorDao.listForUp(up_id,n);
    }
}
