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

    @PostMapping("/addGood")
    public JSONObject addGood(Good good) {
        good.setUsingNumber(0);
        good.setApplyingNumber(0);
        good.setImages("aa");
        return goodService.addGood(good);
    }

    @PostMapping("/addGoodsNumber")
    public JSONObject addGood(@RequestParam String identifier,@RequestParam int addnumber) {

            return goodService.addGoodsNumber(identifier,addnumber);
    }

    @GetMapping("/getGoodList")
    public JSONObject getGoodList() {
        return goodService.viewGoods();
    }

}
