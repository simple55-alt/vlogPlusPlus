package com.vlogplusplus.service;

import com.vlogplusplus.entity.Letter;
import java.util.List;

public interface ILetterService {
    void add(int sender_id,int receiver_id,String var,byte state);
    List<Letter> get_news(int receiver_id);
    List<Letter> listForReceiver(int receiver_id);
    List<Letter> listForSender(int sender_id);
}