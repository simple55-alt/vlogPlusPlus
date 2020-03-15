package com.vlogplusplus.service;

import com.vlogplusplus.entity.Draft;
import com.vlogplusplus.entity.Resp;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDraftService {
    List<Draft> listByVideo(int video_id);
    Draft get_id(int id);
    void add(int video_id,String draft_image);
    void update(int video_id,String draft_image,int id);
    void del(int id);
    Resp<String> upload_img(MultipartFile file);
}
