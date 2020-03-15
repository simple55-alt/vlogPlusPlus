package com.vlogplusplus.service;

import com.vlogplusplus.entity.Comment;
import com.vlogplusplus.entity.Resp;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICommentService {
    void add(int u_id, int target_id, String var, String image, int count);
    List<Comment> listByUid(int u_id);
    List<Comment> listByVideo(int target_id);
    List<Comment> listByTemplate(int target_id);
    void del(int id);
    Resp<String> upload_img(MultipartFile file);
}
