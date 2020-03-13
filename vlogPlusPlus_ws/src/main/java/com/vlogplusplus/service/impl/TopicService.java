package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.ITopicDao;
import com.vlogplusplus.entity.Topic;
import com.vlogplusplus.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService implements ITopicService {
    @Autowired
    private ITopicDao iTopicDao;

    @Override
    public List<Topic> list() {
        return iTopicDao.list();
    }
}
