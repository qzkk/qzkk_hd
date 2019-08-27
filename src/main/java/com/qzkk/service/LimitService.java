package com.qzkk.service;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Good;

public interface LimitService {
    JSONObject limitSZ(long uid,int type);
}
