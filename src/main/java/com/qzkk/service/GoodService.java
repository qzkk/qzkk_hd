package com.qzkk.service;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Good;
import com.qzkk.domain.GoodApplication;

/**
 * @author: jzc
 * @date: 16/7/2019-下午1:16
 * @description:
 */
public interface GoodService {

    //添加一种物资
    JSONObject addGood(Good good);

    //删除一种物资
    JSONObject delGood(long gid);

    //修改物资信息
    JSONObject modifyGood(Good good);

    //查看物资
    JSONObject viewGoods();

    //申请一种物资
    JSONObject addGoodApplication(GoodApplication goodApplication);

    //审核通过物资申请
    JSONObject examineApplication(long gaid);

    //拒绝通过物资申请
    JSONObject refuseApplication(long gaid);
}
