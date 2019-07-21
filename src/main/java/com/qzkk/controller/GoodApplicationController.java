package com.qzkk.controller;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.GoodApplication;
import com.qzkk.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jzc
 * @date: 18/7/2019-下午2:17
 * @description:
 */
@RestController
public class GoodApplicationController {
    @Autowired
    GoodService goodService;

    @PostMapping("/addGoodApplication")
    public JSONObject addGoodApplication(GoodApplication goodApplication) {
        return goodService.addGoodApplication(goodApplication);
    }

    @PostMapping("/examineApplication")
    public JSONObject examineApplication(@RequestParam long gaid) {
         return goodService.examineApplication(gaid);
    }

    @PostMapping("/refuseApplication")
    public JSONObject refuseApplication(@RequestParam long gaid) {
        return goodService.refuseApplication(gaid);
    }

}
