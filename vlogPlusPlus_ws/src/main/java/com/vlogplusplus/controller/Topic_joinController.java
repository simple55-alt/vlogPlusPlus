package com.vlogplusplus.controller;

import com.vlogplusplus.entity.Topic_join;
import com.vlogplusplus.service.ITopic_joinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/topic_join")
public class Topic_joinController {
    @Autowired
    private ITopic_joinService iTopic_joinService;

    @RequestMapping(value = "/lisByTopic", method = RequestMethod.POST)
    private List<Topic_join> lisByTopic(@RequestParam int topic_id){
        return iTopic_joinService.lisByTopic(topic_id);
    }

    @RequestMapping(value = "/lisByUid", method = RequestMethod.POST)
    private List<Topic_join> lisByUid(@RequestParam int u_id){
        return iTopic_joinService.lisByUid(u_id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Topic_join topic_join){
        iTopic_joinService.add(topic_join.getU_id(),topic_join.getTopic_id());
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    private void del(@RequestParam int id) { iTopic_joinService.del(id); }
}
