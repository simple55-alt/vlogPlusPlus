package com.vlogplusplus.dao;

import com.vlogplusplus.entity.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IActivityDao {
    List<Activity> list();
}
