package com.vlogplusplus.service;

import com.vlogplusplus.entity.Activity;

import java.util.Date;
import java.util.List;

public interface IActivityService {
    List<Activity> list();
    void add(String title, String type, String var,
             Date begin_time,Date end_time, String method,String image);
}