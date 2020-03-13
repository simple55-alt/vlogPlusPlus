package com.vlogplusplus.dao;


import com.vlogplusplus.entity.Topic;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopicDao {
    List<Topic> list();
}
