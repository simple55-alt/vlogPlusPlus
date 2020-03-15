package com.vlogplusplus.service.impl;


import com.vlogplusplus.dao.IFavoriteDao;
import com.vlogplusplus.entity.Favorite;
import com.vlogplusplus.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService implements IFavoriteService {
    @Autowired
    private IFavoriteDao iFavoriteDao;

    @Override
    public List<Favorite> list(int u_id) {
        return iFavoriteDao.list(u_id);
    }
}
