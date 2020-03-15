package com.vlogplusplus.dao;

import com.vlogplusplus.entity.Draft;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDraftDao {
    List<Draft> listByVideo(@Param("video_id") int video_id);
    Draft get_id(@Param("id") int id);
    void add(@Param("video_id") int video_id,@Param("draft_image") String draft_image);
    void update(@Param("video_id") int video_id,@Param("draft_image") String draft_image,@Param("id") int id);
    void del(@Param("id") int id);
}