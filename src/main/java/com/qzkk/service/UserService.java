package com.qzkk.service;

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
}
