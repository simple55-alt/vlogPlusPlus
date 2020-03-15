package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.IComplaintDao;
import com.vlogplusplus.entity.Complaint;
import com.vlogplusplus.service.IComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService implements IComplaintService {
    @Autowired
    private IComplaintDao iComplaintDao;

    @Override
    public List<Complaint> list() {
        return iComplaintDao.list();
    }

    @Override
    public void add(int u_id, String var) {
        iComplaintDao.add(u_id,var);
    }
}
