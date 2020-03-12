package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Activity;
import com.vlogplusplus.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
