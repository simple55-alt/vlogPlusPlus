package com.vlogplusplus.dao;

import com.vlogplusplus.entity.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IActivityDao {
    List<Activity> list();
    void add(@Param("title") String title, @Param("type") String type, @Param("var") String var,
             @Param("begin_time") Date begin_time,@Param("end_time") Date end_time, @Param("method") String method,
             @Param("image") String image);
    List<Activity> list_new(@Param("n") int n);
    void update(@Param("title") String title, @Param("type") String type, @Param("var") String var,
                @Param("begin_time") Date begin_time,@Param("end_time") Date end_time, @Param("method") String method,
                @Param("image") String image,@Param("id") int id);
    void del(@Param("id") int id);
}
