package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.ILikesDao;
import com.vlogplusplus.entity.Likes;
import com.vlogplusplus.service.ILikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesService implements ILikesService {
    @Autowired
    private ILikesDao iLikesDao;

    @Override
    public List<Likes> list(int u_id, int n) {
        return iLikesDao.list(u_id,n);
    }

    @Override
    public void add(int u_id, int video_id) {
        iLikesDao.add(u_id, video_id);
    }

    @Override
    public void del(int id) {
        iLikesDao.del(id);
    }
}
