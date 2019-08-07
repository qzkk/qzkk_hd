package com.qzkk.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qzkk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jzc
 * @date: 14/7/2019-下午2:58
 * @description:
 */
@RestController
public class ExamineController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUnexamineUsers")
    public JSONObject getUnexamineUsers() {
        return userService.notAuditedUsers(0);
    }

    @PostMapping("/examinationPassed")
    public JSONObject examinationPassed(@RequestParam long uid,
                                        @RequestParam String account) {
        return userService.auditedUser(uid, account);
    }

    @PostMapping("/unexaminationPassed")
    public JSONObject unexaminationPassed(@RequestParam long uid,
                                          @RequestParam String account) {
        return userService.unauditedUser(uid, account);
        //测试一下
    }

}
