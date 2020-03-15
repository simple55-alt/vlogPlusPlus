package com.vlogplusplus.dao;


import com.vlogplusplus.entity.Likes;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikesDao {
    List<Likes> list(@Param("u_id") int u_id,@Param("n") int n);
    void add(@Param("u_id") int u_id, @Param("video_id") int video_id);
    void del(@Param("id") int id);
}
