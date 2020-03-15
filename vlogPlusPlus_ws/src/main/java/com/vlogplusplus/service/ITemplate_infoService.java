package com.vlogplusplus.service;

import com.vlogplusplus.entity.Template_info;

import java.util.List;

public interface ITemplate_infoService {
    List<Template_info> list();
    void add(String name,String summary,String detail,int u_id);
    void update(String name,String summary,String detail,int u_id,int id);
    void del(int id);
}