package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.ICommentDao;
import com.vlogplusplus.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentDao iCommentDao;

    @Override
    public void add(int u_id, int target_id, String var, String image, int count) {
        iCommentDao.add(u_id,target_id,var,image,count);
    }
}
