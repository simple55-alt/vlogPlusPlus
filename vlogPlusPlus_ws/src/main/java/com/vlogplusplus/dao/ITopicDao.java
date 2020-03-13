package com.vlogplusplus.dao;


import com.vlogplusplus.entity.Topic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopicDao {
    List<Topic> list();
    List<Topic> list_new(@Param("n") int n);
    List<Topic> list_max(@Param("n") int n);
    void update(@Param("title") String title, @Param("summary") String summary, @Param("video") String video,
                @Param("id") int id);
    void del(@Param("id") int id);
}
