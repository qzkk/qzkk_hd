package com.qzkk.controller;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;
    @PostMapping("/getTaskList")
    public JSONObject getTaskList(@RequestParam long tid) {
        return taskService.getTaskList(tid);
    }
    @PostMapping("/getTaskList1")
    public JSONObject getTaskList1() {
        return taskService.getTaskList1();
    }
}
