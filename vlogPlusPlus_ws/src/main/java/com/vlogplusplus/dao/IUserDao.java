package com.vlogplusplus.dao;

import com.vlogplusplus.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {
    User login(@Param("username") String username, @Param("password") String password);
    User get_user(@Param("u_id") int u_id);
}
