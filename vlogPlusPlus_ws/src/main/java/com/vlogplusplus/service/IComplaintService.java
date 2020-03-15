package com.vlogplusplus.service;

import com.vlogplusplus.entity.Complaint;

import java.util.List;

public interface IComplaintService {
    List<Complaint> list();
    void add(int u_id, String var);
}
