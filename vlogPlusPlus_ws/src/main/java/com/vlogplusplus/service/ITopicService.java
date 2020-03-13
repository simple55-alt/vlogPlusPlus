package com.vlogplusplus.service;

import com.vlogplusplus.entity.Topic;

import java.util.List;

public interface ITopicService {
    List<Topic> list();
    List<Topic> list_new(int n);
    List<Topic> list_max(int n);
}
