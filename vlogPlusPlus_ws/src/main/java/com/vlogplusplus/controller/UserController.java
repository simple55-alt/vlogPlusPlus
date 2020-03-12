package com.vlogplusplus.controller;

import com.vlogplusplus.entity.User;
import com.vlogplusplus.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private IUserService iLoginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private User login(@RequestBody User user){
        return iLoginService.login(user.getUsername(), user.getPassword());
    }
}
//zs