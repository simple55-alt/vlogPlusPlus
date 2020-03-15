package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Comment;
import com.vlogplusplus.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Comment comment){
        iCommentService.add(comment.getU_id(),comment.getTarget_id(),comment.getVar(),comment.getImage(),
                comment.getCount());
    }

    @RequestMapping(value = "/listByUid", method = RequestMethod.POST)
    private List<Comment> listByUid(@RequestParam int u_id){
        return iCommentService.listByUid(u_id);
    }

    @RequestMapping(value = "/listByVideo", method = RequestMethod.POST)
    private List<Comment> listByVideo(@RequestParam int target_id){
        return iCommentService.listByVideo(target_id);
    }

    @RequestMapping(value = "/listByTemplate", method = RequestMethod.POST)
    private List<Comment> listByTemplate(@RequestParam int target_id){
        return iCommentService.listByTemplate(-target_id);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    private void del(@RequestParam int id) { iCommentService.del(id); }
}
