package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Favorite;
import com.vlogplusplus.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/favorite")
public class FavoriteController {
    @Autowired
    private IFavoriteService iFavoriteService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private List<Favorite> list(@RequestParam int u_id){
        return iFavoriteService.list(u_id);
    }
}
