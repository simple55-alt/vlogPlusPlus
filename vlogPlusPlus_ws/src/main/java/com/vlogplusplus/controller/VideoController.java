package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Activity;
import com.vlogplusplus.entity.Video;
import com.vlogplusplus.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
