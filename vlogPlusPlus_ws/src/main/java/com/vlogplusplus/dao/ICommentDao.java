package com.vlogplusplus.dao;


import com.vlogplusplus.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentDao {
    void add(@Param("u_id") int u_id,@Param("target_id") int target_id,
             @Param("var") String var,@Param("image") String image,@Param("count") int count);
    List<Comment> listByUid(@Param("u_id") int u_id);
}