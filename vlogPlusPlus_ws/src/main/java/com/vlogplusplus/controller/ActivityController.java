package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Activity;
import com.vlogplusplus.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/activity")
public class ActivityController {
    @Autowired
    private IActivityService iActivityService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private List<Activity> list(){
        return iActivityService.list();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Activity activity){
        iActivityService.add(activity.getTitle(),activity.getType(),activity.getVar(),activity.getBegin_time(),activity.getEnd_time(),
                activity.getMethod(),activity.getImage());
    }
}
