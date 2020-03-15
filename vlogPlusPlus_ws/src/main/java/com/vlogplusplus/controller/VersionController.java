package com.vlogplusplus.controller;

import com.vlogplusplus.entity.Version;
import com.vlogplusplus.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/version")
public class VersionController {
    @Autowired
    private IVersionService iVersionService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private List<Version> list(){
        return iVersionService.list();
    }

    @RequestMapping(value = "/get_latest", method = RequestMethod.POST)
    private List<Version> get_latest(){
        return iVersionService.get_latest();
    }
}
