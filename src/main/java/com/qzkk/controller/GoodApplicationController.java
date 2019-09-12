package com.qzkk.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.BigIntegerNode;
import com.google.gson.JsonObject;
import com.qzkk.domain.GoodApplication;
import com.qzkk.domain.ReturnApplication;
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

    /**
     * 通过其物资申请（暂时用上边的）
     * @param gaid
     * @return
     */
    @PostMapping("/examineApplication")
    public JSONObject examineApplication(@RequestParam long gaid) {
         return goodService.examineApplication(gaid);
    }

    /**
     * 拒绝其物资申请（暂时用上边的）
     * @param gaid
     * @return
     */
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
     * @return
     */
    @PostMapping("/returnGoods")
    public JSONObject returnGoods(ReturnApplication returnApplication) {
        return goodService.returnGoods(returnApplication);
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

    /**
     * 获得要归还物资的申请列表
     * @return
     */
    @PostMapping("/getRetrunApplication")
    public JSONObject getRetrunApplication(){
        return goodService.getRetrunApplication();
    }

    /**
     * 基地确认物资归还
     * @param gid 物资id
     * @param retrunNumber 归还数量
     * @param gaId 物资申请时的id
     * @return
     */
    @PostMapping("/accessReturn")
    public JSONObject accessReturn(@RequestParam long gid,@RequestParam int retrunNumber,@RequestParam long gaId){
        return goodService.accessReturn(gid,retrunNumber,gaId);
    }

    /**
     * 拒绝归还申请
     * @param gaId
     * @return
     */
    @PostMapping("/refuseReturn")
    public JSONObject refuseReturn(@RequestParam long gaId){
        return goodService.refuseReturn(gaId);
    }

    /**
     * 取消物资归还申请
     * @param gaid 物资申请id
     * @return
     */
    @PostMapping("/abandonReturn")
    public JSONObject abandonReturn(@RequestParam long gaid){
        return goodService.abandonReturn(gaid);
    }

}
