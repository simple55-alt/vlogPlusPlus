package com.vlogplusplus.dao;


import com.vlogplusplus.entity.Favorite;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFavoriteDao {
    List<Favorite> list(@Param("u_id") int u_id);
    void add(@Param("u_id") int u_id, @Param("video_id") int video_id);
}
