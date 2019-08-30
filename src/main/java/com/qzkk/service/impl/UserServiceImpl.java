package com.qzkk.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.UserRepository;
import com.qzkk.domain.User;
import com.qzkk.service.UserService;
import com.qzkk.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author: jzc
 * @date: 13/7/2019-下午7:25
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public JSONObject register(User user) {
        JSONObject res = new JSONObject();
        List<User> tempUser = null;

        //排除account重复的情况
        tempUser = userRepository.findByAccount(user.getAccount());
        if(!tempUser.isEmpty()) {
            res.put("code", "403");
            res.put("msg", "account has exist!");
            return res;
        }
        String password = user.getPsd();
        MD5Util md5Util = new MD5Util();
        password = md5Util.encode(password);
        user.setPsd(password);
        user.setState(0);
        user.setExamine(0);

        userRepository.save(user);

        res.put("code", "200");
        res.put("msg", "commit successfully!");
        return res;
    }

    @Override
    public JSONObject login(String account, String psd) {
        JSONObject res = new JSONObject();
        User user = new User();
        List<User> tempUser = null;
        MD5Util md5Util = new MD5Util();

        tempUser = userRepository.findByAccount(account);
        if(tempUser.isEmpty()) {
            res.put("code", "400");
            res.put("msg", "don't exit user");
            return res;
        }

        psd = md5Util.encode(psd);
        user = userRepository.findByAccountAndPsd(account, psd);
        if(user == null) {
            res.put("code", "401");
            res.put("msg", "password is wrong!");
            return res;
        } else if(user.getExamine() == 0) {
            res.put("code", "402");
            res.put("msg", "Not be verified!");
            return res;
        }

        try {
            res = (JSONObject) JSONObject.toJSON(user);
        } catch (Exception e) {
            user.setPageOffset(0);
            user.setPageSize(0);
            res = (JSONObject) JSONArray.toJSON(user);
        }
        res.put("code", "200");
        return res;
    }

    @Override
    public JSONObject notAuditedUsers(int i) {
        JSONObject res = new JSONObject();
        JSONArray usersArray = new JSONArray();

        List<User> users = userRepository.findByExamineEquals(i);
        if(users.isEmpty()) {
            res.put("code", "404");
            res.put("msg", "don't exit unexamine users!");
            return res;
        } else {
            usersArray = (JSONArray) JSONArray.toJSON(users);
            res.put("code", "200");
            res.put("msg", "query users successfully!");
            res.put("length", usersArray.size());
            res.put("users", usersArray);
            return res;
        }



    }

    @Override
    public JSONObject auditedUser(long uid, String account) {
        JSONObject res = new JSONObject();

        User oldUser = userRepository.findByUId(uid);
        oldUser.setExamine(1);
        userRepository.save(oldUser);
        res.put("code", "200");
        res.put("msg", "examine pass!");
        return res;
    }

    @Override
    public JSONObject unauditedUser(long uid, String account) {
        JSONObject res = new JSONObject();

        User oldUser = userRepository.findByUId(uid);
        oldUser.setExamine(-1);
        userRepository.save(oldUser);
        res.put("code", "200");
        res.put("msg", "unexamine pass!");
        return res;
    }

    @Override
    public JSONObject getUsers() {
        JSONObject res = new JSONObject();
        JSONArray usersArray = new JSONArray();

        List<User> users = userRepository.findByExamineEquals(1);
        usersArray = (JSONArray) JSONArray.toJSON(users);
        res.put("users", usersArray);
        res.put("length", usersArray.size());
        res.put("code", "200");
        res.put("msg", "query successfully!");
        return res;
    }
}
