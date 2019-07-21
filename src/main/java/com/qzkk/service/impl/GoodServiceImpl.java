package com.qzkk.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.GoodApplicationRepository;
import com.qzkk.dao.GoodRepository;
import com.qzkk.domain.Good;
import com.qzkk.domain.GoodApplication;
import com.qzkk.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: jzc
 * @date: 16/7/2019-下午1:52
 * @description:
 */
@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private GoodApplicationRepository goodApplicationRepository;

    @Override
    public JSONObject addGood(Good good) {
        JSONObject res = new JSONObject();

        String identifier = good.getIdentifier();
        Good tempGood = goodRepository.findByIdentifier(identifier);
        if(tempGood != null) {
            res.put("code", "");
            res.put("msg", "该物资已经存在！");
            return res;
        } else {
            goodRepository.save(good);
            res.put("code", "200");
            res.put("msg", "该物资已经存在！");
            return res;
        }
    }

    @Override
    public JSONObject delGood(long gid) {
        JSONObject res = new JSONObject();
        Good oldGood = goodRepository.findByGId(gid);
        oldGood.setDel(1);
        goodRepository.save(oldGood);
        res.put("code", "200");
        res.put("msg", "success");
        return res;
    }

    @Override
    public JSONObject modifyGood(Good good) {
        JSONObject res = new JSONObject();
        goodRepository.save(good);
        res.put("code", "200");
        res.put("msg", "success");
        return res;
    }

    @Override
    public JSONObject viewGoods() {
        JSONObject res = new JSONObject();
        List<Good> goods = goodRepository.findAll();
        JSONArray goodsArray = new JSONArray();

        goodsArray = (JSONArray) JSONArray.toJSON(goods);
        res.put("code", "200");
        res.put("msg", "success");
        res.put("goods", goodsArray);
        res.put("length", goodsArray.size());
        return res;
    }

    @Override
    public JSONObject addGoodApplication(GoodApplication goodApplication) {
        JSONObject res = new JSONObject();

        goodApplicationRepository.save(goodApplication);
        res.put("code", "200");
        res.put("msg", "submit successfully");

        return res;

    }

    @Override
    public JSONObject examineApplication(long gaid) {
        JSONObject res = new JSONObject();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        GoodApplication oldGoodApplication = goodApplicationRepository.findByGaId(gaid);
        oldGoodApplication.setState(1);
        oldGoodApplication.setStartDate(new Date());
        goodApplicationRepository.save(oldGoodApplication);

        res.put("code", "200");
        res.put("msg", "submit successfully");
        return res;
    }

    @Override
    public JSONObject refuseApplication(long gaid) {
        JSONObject res = new JSONObject();

        GoodApplication oldGoodApplication = goodApplicationRepository.findByGaId(gaid);
        oldGoodApplication.setState(-1);
        goodApplicationRepository.save(oldGoodApplication);

        res.put("msg", "submit successfully");
        res.put("code", "200");
        return res;
    }
}
