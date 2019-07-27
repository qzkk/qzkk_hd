package com.qzkk.controller;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Registration;
import com.qzkk.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/perRegistration")
    public JSONObject perRegistration(Registration registration){
        JSONObject res =new JSONObject();
        try{
            registrationService.save(registration);
            res.put("msg","登记成功");
        }catch (Exception e){
            res.put("msg","登记失败");
        }
        return res;
    }
}
