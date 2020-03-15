package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.ICommentDao;
import com.vlogplusplus.entity.Comment;
import com.vlogplusplus.entity.Resp;
import com.vlogplusplus.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    @Override
    public Resp<String> upload_img(MultipartFile file) {
        if(file.isEmpty()){
            return Resp.customFail("400","文件为空！");
        }
        String OriginalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        String filePath = System.getProperty("user.dir") + "\\upload\\comment_img\\"; //上传评论插图图片
        File dest = new File(filePath+fileName);
        if(!dest.getParentFile().exists()){ //检查目录是否存在
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); //上传文件
        }catch (Exception e){
            e.printStackTrace();
            return Resp.customFail("500",fileName+"上传失败！");
        }
        return Resp.success(fileName+"");
    }
}
