package com.vlogplusplus.controller;

import com.vlogplusplus.entity.Visitor;
import com.vlogplusplus.service.IVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/visitor")
public class VisitorController {
    @Autowired
    private IVisitorService iVisitorService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Visitor visitor){
        iVisitorService.add(visitor.getU_id(),visitor.getUp_id());
    }

    @RequestMapping(value = "/listForVisitor", method = RequestMethod.POST)
    private List<Visitor> listForVisitor(@RequestParam int u_id,int n){
        return iVisitorService.listForVisitor(u_id,n);
    }

    @RequestMapping(value = "/listForUp", method = RequestMethod.POST)
    private List<Visitor> listForUp(@RequestParam int up_id,int n){
        return iVisitorService.listForUp(up_id,n);
    }
}
