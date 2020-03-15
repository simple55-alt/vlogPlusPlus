package com.vlogplusplus.service;

import com.vlogplusplus.entity.Likes;

import java.util.List;

public interface ILikesService {
    List<Likes> list(int u_id, int n);
    void add(int u_id, int video_id);
    void del(int id);
}
