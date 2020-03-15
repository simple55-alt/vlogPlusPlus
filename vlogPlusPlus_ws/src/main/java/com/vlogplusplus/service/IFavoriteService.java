package com.vlogplusplus.service;

import com.vlogplusplus.entity.Favorite;

import java.util.List;

public interface IFavoriteService {
    List<Favorite> list(int u_id);
}
