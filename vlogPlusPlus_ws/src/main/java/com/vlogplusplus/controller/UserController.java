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
}
