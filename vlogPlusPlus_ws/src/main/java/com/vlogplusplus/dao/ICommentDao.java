package com.vlogplusplus.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentDao {
    void add(@Param("u_id") int u_id,@Param("target_id") int target_id,
             @Param("var") String var,@Param("image") String image,@Param("count") int count);
}