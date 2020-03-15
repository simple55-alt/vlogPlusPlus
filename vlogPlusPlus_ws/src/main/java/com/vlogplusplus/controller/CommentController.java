package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Comment;
import com.vlogplusplus.entity.Resp;
import com.vlogplusplus.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private List<Comment> listByVideo(@RequestParam int video){
        return iCommentService.listByVideo(video);
    }

    @RequestMapping(value = "/listByTemplate", method = RequestMethod.POST)
    private List<Comment> listByTemplate(@RequestParam int template){
        return iCommentService.listByTemplate(-template);
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    private void del(@RequestParam int id) { iCommentService.del(id); }

    @RequestMapping(value = "/upload_img", method = RequestMethod.POST)
    private Resp<String> upload_img(@RequestParam("file") MultipartFile file){
        return iCommentService.upload_img(file); //上传文件
    }
}
