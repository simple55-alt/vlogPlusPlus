package com.vlogplusplus.controller;

import com.vlogplusplus.entity.User;
import com.vlogplusplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private User login(@RequestBody User user){
        return iUserService.login(user.getUsername(), user.getPassword());
    }

    @RequestMapping(value = "/get_user", method = RequestMethod.POST)
    private User get_user(@RequestParam int u_id){
        return iUserService.get_user(u_id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody User user) {
         iUserService.add(user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail(),user.getPhone(),
                 user.getImage(),user.getSex(),user.getBirthday(),user.getFashion());
    }

    @RequestMapping(value = "/update_pass", method = RequestMethod.POST)
    private void update_pass(@RequestBody User user) {
        iUserService.update_pass(user.getPassword(),user.getU_id());
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    private void del(@RequestParam int u_id) {
        iUserService.del(u_id);
    }
}
