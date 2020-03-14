package com.vlogplusplus.service;

import com.vlogplusplus.entity.Comment;

import java.util.List;

public interface ICommentService {
    void add(int u_id, int target_id, String var, String image, int count);
    List<Comment> listByUid(int u_id);
    List<Comment> listByVideo(int target_id);
    List<Comment> listByTemplate(int target_id);
    void del(int id);
}
