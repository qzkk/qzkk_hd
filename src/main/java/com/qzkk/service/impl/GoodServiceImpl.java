package com.qzkk.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.GoodRepository;
import com.qzkk.domain.Good;
import com.qzkk.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
