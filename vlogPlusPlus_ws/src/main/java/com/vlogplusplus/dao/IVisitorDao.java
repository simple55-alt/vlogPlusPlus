package com.vlogplusplus.dao;

import com.vlogplusplus.entity.Visitor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVisitorDao {
    void add(@Param("u_id") int u_id,@Param("up_id") int up_id);
    List<Visitor> listForVisitor(@Param("u_id") int u_id,@Param("n") int n);
    List<Visitor> listForUp(@Param("up_id") int up_id,@Param("n") int n);
}
