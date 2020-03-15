package com.vlogplusplus.service;

import com.vlogplusplus.entity.Visitor;

import java.util.List;

public interface IVisitorService {
    void add(int u_id,int up_id);
    List<Visitor> listForVisitor(int u_id,int n);
    List<Visitor> listForUp(int up_id,int n);
}
