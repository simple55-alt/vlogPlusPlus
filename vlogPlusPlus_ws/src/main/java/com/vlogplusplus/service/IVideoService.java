package com.vlogplusplus.service;

import com.vlogplusplus.entity.Resp;
import com.vlogplusplus.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IVideoService {
    Video get_video(int id);
    void add(String title,String type,String var,String subtitle,String content,int u_id,
             int t_id,int count_likes,int count_share,int count_favorite,int count_watch, byte state);
    void update(String title,String type,String var,String subtitle,String content,int u_id,
             int t_id,int count_likes,int count_share,int count_favorite,int count_watch, byte state,int id);
    void del(int id);
    Resp<String> upload_video(MultipartFile file);
}
