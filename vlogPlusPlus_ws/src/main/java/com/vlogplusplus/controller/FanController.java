package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Fan;
import com.vlogplusplus.service.IFanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/fan")
public class FanController {
    @Autowired
    private IFanService iFanService;

    @RequestMapping(value = "/list_fan", method = RequestMethod.POST)
    private List<Fan> list_fan(@RequestParam int up_id){
        return iFanService.list_fan(up_id);
    }

    @RequestMapping(value = "/list_fellow", method = RequestMethod.POST)
    private List<Fan> list_fellow(@RequestParam int fan_id) {
        return iFanService.list_fellow(fan_id);
    }

    @RequestMapping(value = "/add_fan",method = RequestMethod.POST)
    private void add_fan(@RequestParam int fan_id ,int up_id){
        iFanService.add_fan(fan_id,up_id);
    }

    @RequestMapping(value = "/del_fan",method = RequestMethod.POST)
    private void del_fan(@RequestParam int fan_id ,int up_id){
        iFanService.del_fan(fan_id,up_id);
    }
}
