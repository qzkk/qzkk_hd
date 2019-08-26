package com.qzkk.service;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Good;
import com.qzkk.domain.GoodApplication;

import java.math.BigInteger;

/**
 * @author: jzc
 * @date: 16/7/2019-下午1:16
 * @description:
 */
public interface GoodService {

    //添加一种物资
    JSONObject addGood(Good good);
    //增加物资数量
    JSONObject addGoodsNumber(String identifier,int addnumber);

    //删除一种物资
    JSONObject delGood(long gid);

    //修改物资信息
    JSONObject modifyGood(Good good);

    //查看物资
    JSONObject viewGoods();

    //申请一种物资
    JSONObject addGoodApplication(GoodApplication goodApplication);

    //查看待审核物资
    JSONObject getGoodApplicationList(int state);

    //审核通过物资申请
    JSONObject examineApplication(long gaid);

    //拒绝通过物资申请
    JSONObject refuseApplication(long gaid);

    //返回有剩余的物资类型
    JSONObject getLeftGoodTypes();
    JSONObject getGoodAplyByUid(long uid);
    JSONObject abandonApply(long gaid,long gid,int number);
    JSONObject returnGoods(long gaid,long gid ,int number);
    JSONObject deleteApply(long gaid);

    JSONObject delGood(String identifier);

    JSONObject examineGoodApplication(long gaId);
    JSONObject refuseGoodApplication(long gaId);
}
