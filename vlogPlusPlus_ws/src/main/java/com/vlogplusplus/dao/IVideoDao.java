package com.vlogplusplus.dao;


import com.vlogplusplus.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVideoDao {
    List<Video> get_video(@Param("id") int id);
    void add(@Param("title") String title, @Param("type") String type, @Param("var") String var,
             @Param("subtitle") String subtitle, @Param("content") String content, @Param("u_id") int u_id,
             @Param("t_id") int t_id, @Param("count_likes") int count_likes,
             @Param("count_share") int count_share, @Param("count_favorite") int count_favorite,
             @Param("count_watch") int count_watch, @Param("state") byte state);
    void update(@Param("title") String title, @Param("type") String type, @Param("var") String var,
                @Param("subtitle") String subtitle, @Param("content") String content, @Param("u_id") int u_id,
                @Param("t_id") int t_id, @Param("count_likes") int count_likes,
                @Param("count_share") int count_share, @Param("count_favorite") int count_favorite,
                @Param("count_watch") int count_watch, @Param("state") byte state, @Param("id") int id);
    void del(@Param("id") int id);
}