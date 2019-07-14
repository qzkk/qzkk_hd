package com.qzkk.controller;

import com.alibaba.fastjson.JSON;
import com.qzkk.domain.User;
import com.qzkk.dao.UserRepository;
import com.qzkk.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jzc
 * @date: 13/7/2019-下午6:44
 * @description:
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public JSONObject registerPost(User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public JSONObject loginPost(@RequestParam String account,
                          @RequestParam String psd) {
        return userService.login(account, psd);
    }
}
