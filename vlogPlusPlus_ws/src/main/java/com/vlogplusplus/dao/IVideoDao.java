package com.vlogplusplus.dao;


import com.vlogplusplus.entity.Video;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVideoDao {
    List<Video> get_video(@Param("id") int id);
}
