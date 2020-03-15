package com.vlogplusplus.service;

import com.vlogplusplus.entity.Draft;

import java.util.List;

public interface IDraftService {
    List<Draft> listByVideo(int video_id);
    List<Draft> get_id(int id);
    void add(int video_id,String draft_image);
    void update(int video_id,String draft_image,int id);
    void del(int id);
}
