package com.qzkk.controller;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Registration;
import com.qzkk.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/perRegistration")
    public JSONObject perRegistration(Registration registration){
        JSONObject res =new JSONObject();
        try{
            registrationService.save(registration);
            res.put("code","200");
            res.put("msg","已成功提交");
        }catch (Exception e){
            res.put("code","500");
            res.put("msg","提交登记表失败，请重新尝试！");
        }
        return res;
    }

    /**
     * 如果查询框中的内容不为空，则根据条件约束查询并进行分页
     * @param registration
     * @return
     */
    @PostMapping("/findByConditions")
    public JSONObject findByConditions(Registration registration){
        JSONObject res=new JSONObject();
        try {
            JSONObject resData=registrationService
                    .selectToPageByDynamic(registration);
            res.put("code","200");
            //数据本体内容
            res.put("list",resData.get("list"));
            //总共多少条数据，用于前端分页使用
            res.put("totalNum",resData.get("totalNum"));
        }catch (Exception e){
            res.put("code","500");
            res.put("msg","查询失败，请重新尝试！");
        }
        return res;
    }

    /**
     * 如果查询框中的内容为空，则查询全部数据并进行分页
     * @param registration
     * @return
     */
    @PostMapping("/findAllToPage")
    public JSONObject findAllToPage(Registration registration){
        JSONObject res=new JSONObject();
        try {
            Page<Registration> pageObject=registrationService
                    .findAllToPage(registration.getPageOffset(),registration.getPageSize());
            res.put("code","200");
            //数据本体内容
            res.put("list",pageObject.getContent());
            //总共多少条数据，用于前端分页使用
            res.put("totalNum",pageObject.getTotalElements());
        }catch (Exception e){
            res.put("code","500");
            res.put("msg","查询失败，请重新尝试！");
        }
        return res;
    }
}
