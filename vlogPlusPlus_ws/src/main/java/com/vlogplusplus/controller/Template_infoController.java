package com.vlogplusplus.controller;

import com.vlogplusplus.entity.Template_info;
import com.vlogplusplus.service.ITemplate_infoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/template_info")
public class Template_infoController {
    @Autowired
    private ITemplate_infoService iTemplate_infoService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private List<Template_info> list(){
        return iTemplate_infoService.list();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Template_info template_info){
        iTemplate_infoService.add(template_info.getName(),template_info.getSummary(),template_info.getDetail(),
                template_info.getU_id());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private void update(@RequestBody Template_info template_info) {
        iTemplate_infoService.update(template_info.getName(),template_info.getSummary(),template_info.getDetail(),
                template_info.getU_id(),template_info.getId());
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    private void del(@RequestParam int id) { iTemplate_infoService.del(id); }
}
