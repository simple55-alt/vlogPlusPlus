package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Letter;
import com.vlogplusplus.service.ILetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/letter")
public class LetterController {
    @Autowired
    private ILetterService iLetterService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Letter letter){
        iLetterService.add(letter.getSender_id(),letter.getReceiver_id(),letter.getVar(),letter.getState());
    }

    @RequestMapping(value = "/get_news", method = RequestMethod.POST)
    private List<Letter> get_news(@RequestParam int receiver_id ){
        return iLetterService.get_news(receiver_id);
    }

    @RequestMapping(value = "/listForReceiver", method = RequestMethod.POST)
    private List<Letter> listForReceiver(@RequestParam int receiver_id ){
        return iLetterService.listForReceiver(receiver_id);
    }

    @RequestMapping(value = "/listForSender", method = RequestMethod.POST)
    private List<Letter> listForSender(@RequestParam int sender_id ){
        return iLetterService.listForSender(sender_id);
    }


}
