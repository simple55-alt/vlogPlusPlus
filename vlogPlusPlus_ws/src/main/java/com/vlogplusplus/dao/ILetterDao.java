package com.vlogplusplus.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ILetterDao {
    void add(@Param("sender_id") int sender_id,@Param("receiver_id") int receiver_id,
             @Param("var") String var,@Param("state") byte state);
}