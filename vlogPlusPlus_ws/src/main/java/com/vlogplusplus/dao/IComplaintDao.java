package com.vlogplusplus.dao;

import com.vlogplusplus.entity.Complaint;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComplaintDao {
    List<Complaint> list();
    void add(@Param("u_id") int u_id,@Param("var") String var);
}
