package com.qzkk.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.qzkk.domain.GoodApplication;
import com.qzkk.service.GoodService;
import com.qzkk.vo.GetGoodApplyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * @author: jzc
 * @date: 18/7/2019-下午2:17
 * @description:
 */
@RestController
public class GoodApplicationController {
    @Autowired
    private GoodService goodService;

    @PostMapping("/addGoodApplication")
    public JSONObject addGoodApplication(GoodApplication goodApplication) {
        return goodService.addGoodApplication(goodApplication);
    }

    @GetMapping("/getGoodApplicationList")
    public JSONObject getGoodApplicationList() {
        return goodService.getGoodApplicationList(0);
    }

    @PostMapping("/examineGoodApplication")
    public JSONObject examineGoodApplication(@RequestParam long gaId) {
        return goodService.examineGoodApplication(gaId);
//        审批通过物资申请
    }

    @PostMapping("/refuseGoodApplication")
    public JSONObject refuseGoodApplication(@RequestParam long gaId) {
        return goodService.refuseGoodApplication(gaId);
//        拒绝物资申请
    }

    @PostMapping("/examineApplication")
    public JSONObject examineApplication(@RequestParam long gaid) {
         return goodService.examineApplication(gaid);
    }

    @PostMapping("/refuseApplication")
    public JSONObject refuseApplication(@RequestParam long gaid) {
        return goodService.refuseApplication(gaid);
    }

    @GetMapping("/getGoodTypes")
    public JSONObject getGoodTypes() {
        return goodService.getLeftGoodTypes();
    }

    @PostMapping("/getGoodAplyByUid")
    public JSONObject getGoodAplyForUid(@RequestParam long uid) {
        return goodService.getGoodAplyByUid(uid);
    }

    @PostMapping("/abandonApply")
    public JSONObject abandonApply(@RequestParam long gaid,@RequestParam long gid,@RequestParam int number) {
        return goodService.abandonApply(gaid,gid,number);
    }

    @PostMapping("/returnGoods")
    public JSONObject returnGoods(@RequestParam long gaid,@RequestParam long gid,@RequestParam int number) {
        return goodService.returnGoods(gaid,gid,number);
    }
    @PostMapping("/deleteApply")
    public JSONObject deleteApply(@RequestParam long gaid) {
        return goodService.deleteApply(gaid);
    }

}
