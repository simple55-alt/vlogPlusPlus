package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.IActivityDao;
import com.vlogplusplus.entity.Activity;
import com.vlogplusplus.entity.Resp;
import com.vlogplusplus.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class ActivityService implements IActivityService {
    @Autowired
    private IActivityDao iActivityDao;

    @Override
    public List<Activity> list() {
        return iActivityDao.list();
    }

    @Override
    public void add(String title, String type, String var, Date begin_time, Date end_time, String method, String image) {
        iActivityDao.add(title,type,var,begin_time,end_time,method,image);
    }

    @Override
    public List<Activity> list_new(int n) {
        return iActivityDao.list_new(n);
    }

    @Override
    public void update(String title, String type, String var, Date begin_time, Date end_time, String method, String image, int id) {
        iActivityDao.update(title,type,var,begin_time,end_time,method,image,id);
    }

    @Override
    public void del(int id) {
        iActivityDao.del(id);
    }

    @Override
    public Resp<String> upload_img(MultipartFile file) {
        if(file.isEmpty()){
            return Resp.customFail("400","文件为空！");
        }
        String OriginalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        String filePath = System.getProperty("user.dir") + "\\upload\\activity_img\\"; //上传活动图片
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
