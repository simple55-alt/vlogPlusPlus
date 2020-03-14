package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Video;
import com.vlogplusplus.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/video")
public class VideoController {
    @Autowired
    private IVideoService iVideoService;

    @RequestMapping(value = "/get_video", method = RequestMethod.POST)
    private List<Video> get_video(@RequestParam int id){
        return iVideoService.get_video(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Video video){
        iVideoService.add(video.getTitle(),video.getType(), video.getVar(),video.getSubtitle(),
                video.getContent(),video.getU_id(),video.getT_id(),video.getCount_likes(),
                video.getCount_share(),video.getCount_favorite(),video.getCount_watch(),
                video.getState());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private void update(@RequestBody Video video){
        iVideoService.update(video.getTitle(),video.getType(), video.getVar(),video.getSubtitle(),
                video.getContent(),video.getU_id(),video.getT_id(),video.getCount_likes(),
                video.getCount_share(),video.getCount_favorite(),video.getCount_watch(),
                video.getState(),video.getId());
    }
}
