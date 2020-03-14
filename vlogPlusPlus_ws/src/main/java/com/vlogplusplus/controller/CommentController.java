package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Comment;
import com.vlogplusplus.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
