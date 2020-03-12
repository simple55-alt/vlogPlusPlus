package com.vlogplusplus.dao;

import com.vlogplusplus.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IUserDao {
    User login(@Param("username") String username, @Param("password") String password);
    User get_user(@Param("u_id") int u_id);
    void add(@Param("username") String username, @Param("password") String password, @Param("nickname") String nickname, @Param("email") String email,
             @Param("phone") String phone, @Param("image") String image,
             @Param("sex") byte sex, @Param("birthday") Date birthday,@Param("fashion") String fashion);
    void update_pass(@Param("password") String password,@Param("u_id") int u_id);
    void del(@Param("u_id") int u_id);
}