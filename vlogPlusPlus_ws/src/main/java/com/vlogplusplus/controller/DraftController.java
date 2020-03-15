package com.vlogplusplus.controller;

import com.vlogplusplus.entity.Draft;
import com.vlogplusplus.entity.Resp;
import com.vlogplusplus.service.IDraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/draft")
public class DraftController {
    @Autowired
    private IDraftService iDraftService;

    @RequestMapping(value = "/listByVideo", method = RequestMethod.POST)
    private List<Draft> listByVideo(@RequestParam int video_id){
        return iDraftService.listByVideo(video_id);
    }

    @RequestMapping(value = "/get_id", method = RequestMethod.POST)
    private Draft get_id(@RequestParam int id){
        return iDraftService.get_id(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Draft draft){
        iDraftService.add(draft.getVideo_id(),draft.getDraft_image());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private void update(@RequestBody Draft draft){
        iDraftService.update(draft.getVideo_id(),draft.getDraft_image(),draft.getId());
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    private void del(@RequestParam int id) { iDraftService.del(id); }

    @RequestMapping(value = "/upload_img", method = RequestMethod.POST)
    private Resp<String> upload_img(@RequestParam("file") MultipartFile file){
        return iDraftService.upload_img(file); //上传文件
    }
}
