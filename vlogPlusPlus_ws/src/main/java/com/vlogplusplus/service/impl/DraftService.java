package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.IDraftDao;
import com.vlogplusplus.entity.Draft;
import com.vlogplusplus.entity.Resp;
import com.vlogplusplus.service.IDraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class DraftService implements IDraftService {
    @Autowired
    private IDraftDao iDraftDao;

    @Override
    public List<Draft> listByVideo(int video_id) {
        return iDraftDao.listByVideo(video_id);
    }

    @Override
    public Draft get_id(int id) {
        return iDraftDao.get_id(id);
    }

    @Override
    public void add(int video_id, String draft_image) {
        iDraftDao.add(video_id,draft_image);
    }

    @Override
    public void update(int video_id, String draft_image, int id) {
        iDraftDao.update(video_id,draft_image,id);
    }

    @Override
    public void del(int id) {
        iDraftDao.del(id);
    }

    @Override
    public Resp<String> upload_img(MultipartFile file) {
        if(file.isEmpty()){
            return Resp.customFail("400","文件为空！");
        }
        String OriginalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        String filePath = System.getProperty("user.dir") + "\\upload\\draft_img\\"; //上传草稿图片
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
