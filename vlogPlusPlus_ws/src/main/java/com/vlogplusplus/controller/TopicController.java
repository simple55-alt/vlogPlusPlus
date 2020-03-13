package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Topic;
import com.vlogplusplus.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/topic")
public class TopicController {
    @Autowired
    private ITopicService iTopicService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private List<Topic> list(){
        return iTopicService.list();
    }

    @RequestMapping(value = "/list_new", method = RequestMethod.POST)
    private List<Topic> list_new(@RequestParam int n){
        return iTopicService.list_new(n);
    }

    @RequestMapping(value = "/list_max", method = RequestMethod.POST)
    private List<Topic> list_max(@RequestParam int n){
        return iTopicService.list_max(n);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private void update(@RequestBody Topic topic) {
        iTopicService.update(topic.getTitle(), topic.getSummary(), topic.getVideo(), topic.getId());
    }

}
