package com.vlogplusplus.controller;

import com.vlogplusplus.entity.Complaint;
import com.vlogplusplus.service.IComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/complaint")
public class ComplaintController {
    @Autowired
    private IComplaintService iComplaintService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private List<Complaint> list(){
        return iComplaintService.list();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Complaint complaint){
        iComplaintService.add(complaint.getU_id(),complaint.getVar());
    }
}
