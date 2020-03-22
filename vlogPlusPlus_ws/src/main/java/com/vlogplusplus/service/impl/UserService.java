package com.vlogplusplus.service.impl;

import com.vlogplusplus.dao.IUserDao;
import com.vlogplusplus.entity.Resp;
import com.vlogplusplus.entity.User;
import com.vlogplusplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDao iUserDao;

    @Override
    public User login(String username, String password) {
        return iUserDao.login(username, password);
    }

    @Override
    public List<User> check(String username) {
        return iUserDao.check(username);
    }

    @Override
    public User get_user(int u_id) {
        return iUserDao.get_user(u_id);
    }

    @Override
    public void add(String username, String password, String nickname, String email, String phone, String image, byte sex, Date birthday, String fashion) {
        iUserDao.add(username, password, nickname, email, phone, image, sex, birthday, fashion);
    }

    @Override
    public void update_pass(String password, int u_id) {
        iUserDao.update_pass(password, u_id);
    }

    @Override
    public void del(int u_id) {
        iUserDao.del(u_id);
    }

    @Override
    public void update_detail(String nickname, String email, String phone, String image, byte sex, Date birthday, String fashion, int u_id) {
        iUserDao.update_detail(nickname,email,phone,image,sex,birthday,fashion,u_id);
    }

    @Override
    public Resp<String> upload_img(MultipartFile file) {
        if(file.isEmpty()){
            return Resp.customFail("400","文件为空！");
        }
        String OriginalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        String filePath = System.getProperty("user.dir") + "\\upload\\head_img\\"; //上传头像
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
