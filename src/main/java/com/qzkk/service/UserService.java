package com.qzkk.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.User;


/**
 * @author: jzc
 * @date: 13/7/2019-下午7:10
 * @description:
 */
public interface UserService {

    JSONObject register(User user);

    JSONObject login(String account, String psd);

    //获取申请审核的用户
    JSONObject notAuditedUsers(int i);

    //通过注册审核
    JSONObject auditedUser(long uid, String account);

    //不通过注册审核
    JSONObject unauditedUser(long uid, String account);

    //获取所有用户信息
    JSONObject getUsers();
}
