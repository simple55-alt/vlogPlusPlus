package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.ILetterDao;
import com.vlogplusplus.service.ILetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LetterService implements ILetterService {
    @Autowired
    private ILetterDao iLetterDao;

    @Override
    public void add(int sender_id, int receiver_id, String var, byte state) {
        iLetterDao.add(sender_id,receiver_id,var,state);
    }
}
