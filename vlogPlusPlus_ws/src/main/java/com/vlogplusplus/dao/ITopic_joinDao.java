package com.vlogplusplus.dao;

import com.vlogplusplus.entity.Topic_join;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopic_joinDao {
    List<Topic_join> lisByTopic(@Param("topic_id") int topic_id);
    List<Topic_join> lisByUid(@Param("u_id") int u_id);
    void add(@Param("u_id") int u_id,@Param("topic_id") int topic_id);
    void del(@Param("id") int id);

}
