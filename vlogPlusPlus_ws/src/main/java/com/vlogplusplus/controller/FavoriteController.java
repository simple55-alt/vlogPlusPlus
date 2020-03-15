package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Favorite;
import com.vlogplusplus.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Favorite favorite){
         iFavoriteService.add(favorite.getU_id(),favorite.getVideo_id());
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    private void del(@RequestParam int id){
        iFavoriteService.del(id);
    }
}
