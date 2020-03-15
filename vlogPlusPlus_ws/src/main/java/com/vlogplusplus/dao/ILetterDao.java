package com.vlogplusplus.dao;


import com.vlogplusplus.entity.Letter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILetterDao {
    void add(@Param("sender_id") int sender_id,@Param("receiver_id") int receiver_id,
             @Param("var") String var,@Param("state") byte state);
    List<Letter> get_news(@Param("receiver_id") int receiver_id);
    List<Letter> listForReceiver(@Param("receiver_id") int receiver_id);
    List<Letter> listForSender(@Param("sender_id") int sender_id);
}