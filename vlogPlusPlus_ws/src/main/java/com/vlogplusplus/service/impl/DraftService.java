package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.IDraftDao;
import com.vlogplusplus.entity.Draft;
import com.vlogplusplus.service.IDraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DraftService implements IDraftService {
    @Autowired
    private IDraftDao iDraftDao;

    @Override
    public List<Draft> listByVideo(int video_id) {
        return iDraftDao.listByVideo(video_id);
    }

    @Override
    public List<Draft> get_id(int id) {
        return iDraftDao.get_id(id);
    }

    @Override
    public void add(int video_id, String draft_image) {
        iDraftDao.add(video_id,draft_image);
    }

    @Override
    public void update(int video_id, String draft_image, int id) {
        iDraftDao.update(video_id,draft_image,id);
    }

    @Override
    public void del(int id) {
        iDraftDao.del(id);
    }
}
