package com.qzkk.controller;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.UserRepository;
import com.qzkk.domain.User;
import com.qzkk.service.LimitService;
import com.qzkk.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {
    @Autowired
    private LimitService limitService;
    @PostMapping("/limitSZ")
    public JSONObject limitSZ(@RequestParam long uid,@RequestParam int type) {
        return limitService.limitSZ(uid,type);
    }
}
