package com.qzkk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.TaskRepository;
import com.qzkk.domain.Task;
import com.qzkk.service.TaskService;
import com.qzkk.utils.CastEntity;
import com.qzkk.vo.GoodAplyInfo;
import com.qzkk.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public JSONObject getTaskList(long teamId) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.getTaskListByTeamId(teamId);
            List<TaskVO> taskVOS = CastEntity.castEntity(list, TaskVO.class);

            res.put("list",taskVOS);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject getTaskList1() {
        JSONObject res = new JSONObject();
        try{
            List<Task> list=taskRepository.findAll();
            res.put("list",list);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }
}
