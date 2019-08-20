package com.qzkk.service;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Task;
import com.qzkk.domain.Team;

public interface TaskService {
    JSONObject getTaskList(long teamId);
    JSONObject getTaskList1();

}
