package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.ITopic_joinDao;
import com.vlogplusplus.entity.Topic_join;
import com.vlogplusplus.service.ITopic_joinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Topic_joinService implements ITopic_joinService {
    @Autowired
    private ITopic_joinDao iTopic_joinDao;

    @Override
    public List<Topic_join> lisByTopic(int topic_id) {
        return iTopic_joinDao.lisByTopic(topic_id);
    }

    @Override
    public List<Topic_join> lisByUid(int u_id) {
        return iTopic_joinDao.lisByUid(u_id);
    }

    @Override
    public void add(int u_id, int topic_id) {
        iTopic_joinDao.add(u_id,topic_id);
    }

    @Override
    public void del(int id) {
        iTopic_joinDao.del(id);
    }
}
