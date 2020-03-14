package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.IVideoDao;
import com.vlogplusplus.entity.Video;
import com.vlogplusplus.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService implements IVideoService {
    @Autowired
    private IVideoDao iVideoDao;

    @Override
    public List<Video> get_video(int id) {
        return iVideoDao.get_video(id);
    }

    @Override
    public void add(String title, String type, String var, String subtitle, String content, int u_id, int t_id, int count_likes, int count_share, int count_favorite, int count_watch, byte state) {
        iVideoDao.add(title,type,var,subtitle,content,u_id,t_id,count_likes,count_share,count_favorite,
                count_watch,state);
    }

    @Override
    public void update(String title, String type, String var, String subtitle, String content, int u_id, int t_id, int count_likes, int count_share, int count_favorite, int count_watch, byte state, int id) {
        iVideoDao.update(title,type,var,subtitle,content,u_id,t_id,count_likes,count_share,count_favorite,
                count_watch,state,id);
    }

    @Override
    public void del(int id) {
        iVideoDao.del(id);
    }
}
