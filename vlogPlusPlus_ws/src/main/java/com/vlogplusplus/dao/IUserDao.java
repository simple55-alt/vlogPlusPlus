package com.vlogplusplus.dao;

import com.vlogplusplus.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IUserDao {
    User login(@Param("username") String username, @Param("password") String password);
    List<User> check(@Param("username") String username);
    User get_user(@Param("u_id") int u_id);
    void add(@Param("username") String username, @Param("password") String password, @Param("nickname") String nickname, @Param("email") String email,
             @Param("phone") String phone, @Param("image") String image,
             @Param("sex") byte sex, @Param("birthday") Date birthday,@Param("fashion") String fashion);
    void update_pass(@Param("password") String password,@Param("u_id") int u_id);
    void del(@Param("u_id") int u_id);
    void update_detail(@Param("nickname") String nickname,@Param("email") String email,@Param("phone") String phone,@Param("image") String image,
                       @Param("sex") byte sex,@Param("birthday") Date birthday,@Param("fashion") String fashion,@Param("u_id") int u_id );
}
