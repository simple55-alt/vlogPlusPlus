package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.ICommentDao;
import com.vlogplusplus.entity.Comment;
import com.vlogplusplus.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentDao iCommentDao;

    @Override
    public void add(int u_id, int target_id, String var, String image, int count) {
        iCommentDao.add(u_id,target_id,var,image,count);
    }

    @Override
    public List<Comment> listByUid(int u_id) {
        return iCommentDao.listByUid(u_id);
    }

    @Override
    public List<Comment> listByVideo(int target_id) {
        return iCommentDao.listByVideo(target_id);
    }

    @Override
    public List<Comment> listByTemplate(int target_id) {
        return iCommentDao.listByTemplate(target_id);
    }

    @Override
    public void del(int id) {
        iCommentDao.del(id);
    }
}
