package com.qzkk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.UserRepository;
import com.qzkk.domain.User;
import com.qzkk.service.LimitService;
import com.qzkk.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimitServiceImpl implements LimitService {
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public JSONObject limitSZ(long uid,int type) {
        JSONObject res=new JSONObject();
        try{
            User user=userRepository.findByUId(uid);
            user.setType(type);
            userRepository.save(user);
            res.put("code","200");
            res.put("msg","权限设置成功");
        }catch (Exception e){
            res.put("code","500");
            res.put("msg","权限设置失败");
        }

        return res;
    }
}
