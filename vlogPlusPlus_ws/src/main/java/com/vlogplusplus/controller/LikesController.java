package com.vlogplusplus.controller;

import com.vlogplusplus.entity.Likes;
import com.vlogplusplus.service.ILikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/likes")
public class LikesController {
    @Autowired
    private ILikesService iLikesService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private List<Likes> list(@RequestParam int u_id, int n){
        return iLikesService.list(u_id,n);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Likes likes){
        iLikesService.add(likes.getU_id(),likes.getVideo_id());
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    private void del(@RequestParam int id){
        iLikesService.del(id);
    }
}
