package com.vlogplusplus.service;

import com.vlogplusplus.entity.Topic_join;

import java.util.List;

public interface ITopic_joinService {
    List<Topic_join> lisByTopic(int topic_id);
    List<Topic_join> lisByUid(int u_id);
    void add(int u_id,int topic_id);
    void del(int id);
}
