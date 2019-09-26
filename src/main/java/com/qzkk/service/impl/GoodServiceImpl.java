package com.qzkk.service.impl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.GoodApplicationRepository;
import com.qzkk.dao.GoodRepository;
import com.qzkk.dao.ReturnApplicationRepository;
import com.qzkk.domain.Good;
import com.qzkk.domain.GoodApplication;
import com.qzkk.domain.ReturnApplication;
import com.qzkk.service.GoodService;
import com.qzkk.utils.CastEntity;
import com.qzkk.vo.GetGoodApplyInfo;
import com.qzkk.vo.GoodAplyInfo;
import com.qzkk.vo.GoodMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private ReturnApplicationRepository returnApplicationRepository;

    @Override
    public JSONObject addGood(Good good) {
        JSONObject res = new JSONObject();

        String identifier = good.getIdentifier();
        Good tempGood = goodRepository.findByIdentifier(identifier);
        if(tempGood != null) {
            res.put("code", "404");
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
    public JSONObject addGoodsNumber(String identifier,int addnumber){
        JSONObject res = new JSONObject();
        Good good = goodRepository.findByIdentifier(identifier);
        good.setNumber(good.getNumber()+addnumber);
        goodRepository.save(good);
        res.put("code","200");
        res.put("msg","success");
        return res;
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
        Good oldGood = goodRepository.findByGId(goodApplication.getGId());
        int usingNum = oldGood.getUsingNumber();
        int totalNum = oldGood.getNumber();
        int nowApplyNum = goodApplication.getNumber();
        if(totalNum - usingNum <= 0) {
            res.put("code","501");
            res.put("msg", "该物资已经申请完了！！");
            return res;
        } else if(totalNum - usingNum - nowApplyNum < 0) {
            res.put("code","501");
            res.put("msg", "申请数量以超出申请数量！！");
            return res;
        } else {
            oldGood.setApplyingNumber(oldGood.getApplyingNumber() + goodApplication.getNumber());
            goodRepository.save(oldGood);
            goodApplicationRepository.save(goodApplication);
            res.put("code", "200");
            res.put("msg", "submit successfully");
            return res;
        }
    }


//    查询待审核物资
    @Override
    public JSONObject getGoodApplicationList(int state){
        JSONObject res =new JSONObject();
        try{
        List<Object[]> goodApplications = goodApplicationRepository.getGoodApplicationList();
//        把查询出来的信息加入实体类 try方法如果查出来数据库类型和实体类型一致会成功 否则失败
        List<GetGoodApplyInfo> goodsApplicationlists = CastEntity.castEntity(goodApplications, GetGoodApplyInfo.class);
       res.put("data",goodsApplicationlists);
       res.put("code","200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
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

    @Override
    public JSONObject getLeftGoodTypes() {
        JSONObject res = new JSONObject();
        List<Good> list = goodRepository.getLeftGoodTypes();
        JSONArray goodsArray = new JSONArray();
        goodsArray = (JSONArray) JSONArray.toJSON(list);

        res.put("data",goodsArray);
        res.put("code", "200");
        return res;

//        for(Map map : result) {
//            JsonObject j = new JsonObject();
//            j.addProperty(attrName, val);
//            list.add(j);
//        }
//        gson.toJson(list);


    }

    @Override
    public JSONObject getGoodAplyByUid(long uid) {
        JSONObject res = new JSONObject();
        try {
            List<Object[]> list = goodApplicationRepository.getGoodAplyInfoByUid(uid);
            List<GoodAplyInfo> goodAplyInfos = CastEntity.castEntity(list, GoodAplyInfo.class);

            res.put("list",goodAplyInfos);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }

        return res;
    }

    @Override
    public JSONObject abandonApply(long gaid,long gid,int number) {
        JSONObject res = new JSONObject();
        try {
            Good oldGood=goodRepository.findByGId(gid);
            oldGood.setApplyingNumber(oldGood.getApplyingNumber()-number);
            goodRepository.save(oldGood);
            goodApplicationRepository.deleteOne(gaid);
            res.put("code", "200");
            res.put("msg", "操作成功");
        }catch (Exception e){
            res.put("code", "500");
            res.put("msg", "操作失败");
        }

        return res;
    }

    @Override
    public JSONObject returnGoods(ReturnApplication returnApplication) {
        JSONObject res = new JSONObject();
        try {
            returnApplicationRepository.save(returnApplication);
            GoodApplication goodApplication=goodApplicationRepository.findByGaId(returnApplication.getGaId());
            goodApplication.setState(2);
            goodApplicationRepository.save(goodApplication);
            res.put("code", "200");
            res.put("msg", "操作成功");
        }catch (Exception e){
            res.put("code", "500");
            res.put("msg", "操作失败");
        }

        return res;
    }

    @Override
    public JSONObject deleteApply(long gaid) {
        JSONObject res = new JSONObject();
        try {
            goodApplicationRepository.deleteOne(gaid);
            res.put("code", "200");
            res.put("msg", "操作成功");
        }catch (Exception e){
            res.put("code", "500");
            res.put("msg", "操作失败");
        }

        return res;
    }

    @Override
    public JSONObject delGood(String identifier){
        JSONObject res = new JSONObject();
        Good delGood =goodRepository.findByIdentifier(identifier);
        if(delGood.getUsingNumber()!=0){
            res.put("code","500");
            res.put("msg","该物资正在使用无法删除");
        }
        else{
        delGood.setDel(1);
        goodRepository.save(delGood);
            res.put("code","200");
            res.put("msg","删除成功");
        }
        return res;
    }

    @Override
    public JSONObject examineGoodApplication(long gaId){
        GoodApplication goodApplication =goodApplicationRepository.findByGaId(gaId);
        goodApplication.setState(1);
        Good good = goodRepository.findByGId(goodApplication.getGId());
        good.setUsingNumber(good.getUsingNumber()+goodApplication.getNumber());
        goodRepository.save(good);
        goodApplicationRepository.save(goodApplication);

        JSONObject res =new JSONObject();
        res.put("code","200");
        res.put("msg","审批通过");
        return res;
//        审核通过小队物资申请 更改物资申请表的状态和物资表中的使用数量
    }

    @Override
    public JSONObject refuseGoodApplication(long gaId) {
        GoodApplication goodApplication =goodApplicationRepository.findByGaId(gaId);
        goodApplication.setState(-1);
        goodApplicationRepository.save(goodApplication);
        JSONObject res = new JSONObject();
        res.put("code","201");
        res.put("msg","审批不通过，请重新提交物资");
        return res;
//        审核拒绝小队物资申请
    }

    @Override
    public JSONObject getRetrunApplication() {
        JSONObject res=new JSONObject();
        try{
            List<ReturnApplication> list=returnApplicationRepository.getReturnAply();
            res.put("list",list);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询出错");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject accessReturn(long gid, int returnNumber,long gaId) {
        JSONObject res=new JSONObject();
        try {
            Good good=goodRepository.findByGId(gid);
            good.setUsingNumber(good.getUsingNumber()-returnNumber);
            goodRepository.save(good);
            GoodApplication goodApplication=goodApplicationRepository.findByGaId(gaId);
            goodApplication.setState(4);
            goodApplicationRepository.save(goodApplication);
            ReturnApplication returnApplication=returnApplicationRepository.findByGaId(gaId);
            returnApplication.setDel(1);
            returnApplicationRepository.save(returnApplication);
            res.put("msg","操作成功");
            res.put("code","200");
        }catch (Exception e){
            res.put("msg","操作失败");
            res.put("code","500");
        }
        return res;
    }

    @Override
    public JSONObject refuseReturn(long gaId) {
        JSONObject res=new JSONObject();
        try {
            GoodApplication goodApplication=goodApplicationRepository.findByGaId(gaId);
            goodApplication.setState(3);
            goodApplicationRepository.save(goodApplication);
            ReturnApplication returnApplication=returnApplicationRepository.findByGaId(gaId);
            returnApplication.setDel(1);
            returnApplicationRepository.save(returnApplication);
            res.put("msg","操作成功");
            res.put("code","200");
        }catch (Exception e){
            res.put("msg","操作失败");
            res.put("code","500");
        }
        return res;
    }

    @Override
    public JSONObject abandonReturn(long gaid) {
        JSONObject res=new JSONObject();
        try {
            GoodApplication goodApplication=goodApplicationRepository.findByGaId(gaid);
            goodApplication.setState(1);
            goodApplicationRepository.save(goodApplication);
            ReturnApplication returnApplication=returnApplicationRepository.findByGaId(gaid);
            returnApplicationRepository.delete(returnApplication);
            res.put("msg","操作成功");
            res.put("code","200");
        }catch (Exception e){
            res.put("msg","操作失败");
            res.put("code","500");
        }
        return res;
    }

    @Override
    public JSONObject goodMessage(String identifier){
        JSONObject res = new JSONObject();
        try {
            List<Object[]> list = goodApplicationRepository.goodMessage(identifier);
            List<GoodMessage> goodMessages = CastEntity.castEntity(list, GoodMessage.class);
            res.put("list",goodMessages);
            res.put("msg","操作成功");
        }catch (Exception e){
            res.put("code",500);
            res.put("msg","失败");
        }
        return res;
    }

}
