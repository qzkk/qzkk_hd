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

    /**
     * 添加一个物资申请
     * @param goodApplication
     * @return
     */
    @PostMapping("/addGoodApplication")
    public JSONObject addGoodApplication(GoodApplication goodApplication) {
        return goodService.addGoodApplication(goodApplication);
    }

    /**
     * 获得物资申请列表
     * @return
     */
    @GetMapping("/getGoodApplicationList")
    public JSONObject getGoodApplicationList() {
        return goodService.getGoodApplicationList(0);
    }

    /**
     * 物资申请审批通过
     * @param gaId
     * @return
     */
    @PostMapping("/examineGoodApplication")
    public JSONObject examineGoodApplication(@RequestParam long gaId) {
        return goodService.examineGoodApplication(gaId);
//        审批通过物资申请
    }

    /**
     * 拒绝其物资申请
     * @param gaId
     * @return
     */
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

    /**
     * 获得物资的种类
     * @return
     */
    @GetMapping("/getGoodTypes")
    public JSONObject getGoodTypes() {
        return goodService.getLeftGoodTypes();
    }

    /**
     * 获得该队长的物资申请列表
     * @param uid
     * @return
     */
    @PostMapping("/getGoodAplyByUid")
    public JSONObject getGoodAplyForUid(@RequestParam long uid) {
        return goodService.getGoodAplyByUid(uid);
    }

    /**
     * 取消该物资申请
     * @param gaid
     * @param gid
     * @param number
     * @return
     */
    @PostMapping("/abandonApply")
    public JSONObject abandonApply(@RequestParam long gaid,@RequestParam long gid,@RequestParam int number) {
        return goodService.abandonApply(gaid,gid,number);
    }

    /**
     * 归还物资
     * @param gaid
     * @param gid
     * @param number
     * @return
     */
    @PostMapping("/returnGoods")
    public JSONObject returnGoods(@RequestParam long gaid,@RequestParam long gid,@RequestParam int number) {
        return goodService.returnGoods(gaid,gid,number);
    }

    /**
     * 删除该物资申请
     * @param gaid
     * @return
     */
    @PostMapping("/deleteApply")
    public JSONObject deleteApply(@RequestParam long gaid) {
        return goodService.deleteApply(gaid);
    }

}
