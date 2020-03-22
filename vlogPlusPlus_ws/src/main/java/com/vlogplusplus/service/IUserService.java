package com.vlogplusplus.service;

import com.vlogplusplus.entity.Resp;
import com.vlogplusplus.entity.User;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.List;

public interface IUserService {
    User login(String username, String password);
    List<User> check(String username);
    User get_user(int u_id);
    void add(String username, String password, String nickname, String email,
             String phone, String image, byte sex,  Date birthday, String fashion);
    void update_pass(String password,int u_id);
    void del(int u_id);
    void update_detail(String nickname, String email,
                       String phone, String image, byte sex,  Date birthday, String fashion,int u_id);
    Resp<String> upload_img(MultipartFile file);
}
