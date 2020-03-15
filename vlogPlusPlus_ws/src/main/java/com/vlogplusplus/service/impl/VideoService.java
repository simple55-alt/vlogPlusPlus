package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.IVideoDao;
import com.vlogplusplus.entity.Resp;
import com.vlogplusplus.entity.Video;
import com.vlogplusplus.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class VideoService implements IVideoService {
    @Autowired
    private IVideoDao iVideoDao;

    @Override
    public List<Video> get_video(int id) {
        return iVideoDao.get_video(id);
    }

    @Override
    public void add(String title, String type, String var, String subtitle, String content, int u_id, int t_id, int count_likes, int count_share, int count_favorite, int count_watch, byte state) {
        iVideoDao.add(title,type,var,subtitle,content,u_id,t_id,count_likes,count_share,count_favorite,
                count_watch,state);
    }

    @Override
    public void update(String title, String type, String var, String subtitle, String content, int u_id, int t_id, int count_likes, int count_share, int count_favorite, int count_watch, byte state, int id) {
        iVideoDao.update(title,type,var,subtitle,content,u_id,t_id,count_likes,count_share,count_favorite,
                count_watch,state,id);
    }

    @Override
    public void del(int id) {
        iVideoDao.del(id);
    }

    @Override
    public Resp<String> upload_video(MultipartFile file) {
        if(file.isEmpty()){
            return Resp.customFail("400","文件为空！");
        }
        String OriginalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        String filePath = System.getProperty("user.dir") + "\\upload\\video\\";
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
