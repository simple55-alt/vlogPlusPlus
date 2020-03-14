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
}
