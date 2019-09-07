package com.qzkk.controller;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Good;
import com.qzkk.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jzc
 * @date: 18/7/2019-上午9:17
 * @description:
 */
@RestController
public class GoodController {
    @Autowired
    private GoodService goodService;

    /**
     * 添加一种新的物资
     * @param good
     * @return
     */
    @PostMapping("/addGood")
    public JSONObject addGood(Good good) {
        good.setUsingNumber(0);
        good.setApplyingNumber(0);
        good.setImages("aa");
        return goodService.addGood(good);
    }

    /**
     * 为物资加数量
     * @param identifier
     * @param addnumber
     * @return
     */
    @PostMapping("/addGoodsNumber")
    public JSONObject addGood(@RequestParam String identifier,@RequestParam int addnumber) {

            return goodService.addGoodsNumber(identifier,addnumber);
    }

    /**
     * 获取物资列表
     * @return
     */
    @GetMapping("/getGoodList")
    public JSONObject getGoodList() {
        return goodService.viewGoods();
    }

    /**
     * 删除物资
     * @param identifier
     * @return
     */
    @PostMapping("/delGood")
    public JSONObject delGood(String identifier){
        return goodService.delGood(identifier);
    }

}
