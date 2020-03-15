package com.vlogplusplus.controller;


import com.vlogplusplus.entity.Activity;
import com.vlogplusplus.entity.Letter;
import com.vlogplusplus.service.ILetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/letter")
public class LetterController {
    @Autowired
    private ILetterService iLetterService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Letter letter){
        iLetterService.add(letter.getSender_id(),letter.getReceiver_id(),letter.getVar(),letter.getState());
    }
}
