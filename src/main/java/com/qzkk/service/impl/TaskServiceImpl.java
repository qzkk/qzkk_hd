package com.qzkk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.TaskRepository;
import com.qzkk.dao.TeamTaskRepository;
import com.qzkk.domain.Task;
import com.qzkk.domain.Team;
import com.qzkk.domain.Team_Task;
import com.qzkk.service.TaskService;
import com.qzkk.utils.CastEntity;
import com.qzkk.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TeamTaskRepository teamTaskRepository;
    @Override
    public JSONObject getTaskList(long teamId) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.getTaskListByTeamId(teamId);
            List<TaskVO> taskVOS = CastEntity.castEntity(list, TaskVO.class);//类已经改了，不好用了，到时候看情况再改

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

    @Override
    public JSONObject getTaskListByUid(long uid) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.getTaskListByUid(uid);
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
    public JSONObject getPassedTaskListByUid(long uid) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.getPassedTaskListByUid(uid);
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
    public JSONObject selectTeamNotDis(long taskid, long uid) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.selectTeamNotDis(taskid,uid);
            List<TeamVO> teamVOS = CastEntity.castEntity(list, TeamVO.class);
            res.put("list",teamVOS);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject deleteTask(long id) {
        JSONObject res = new JSONObject();
        try {
            taskRepository.deleteById(id);
            res.put("msg","操作成功");
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","操作失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject viewTeamsByTaskId(long taid) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.viewTeamsByTaskId(taid);
            List<TeamCaptain> teamCaptains = CastEntity.castEntity(list, TeamCaptain.class);
            res.put("list",teamCaptains);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject aplyTask(Task task) {
        JSONObject res =new JSONObject();
        try {
            taskRepository.save(task);
            res.put("msg","申请成功");
            res.put("code", "200");

        }catch (Exception e){
            res.put("msg","申请失败，请重新尝试");
            res.put("code", "200");

        }
        return res;
    }

    @Override
    public JSONObject distributeTa(List<TeamVO> list,long taid) {
        JSONObject res =new JSONObject();
        for (int i=0;i<list.size();i++){
            Team_Task team_task=new Team_Task();
            team_task.setTaskId(taid);
            team_task.setTeamId(list.get(i).getTid().longValue());
            try {
                teamTaskRepository.save(team_task);
                res.put("code", "200");
                res.put("msg", "分配成功");
            }catch (Exception e){
                res.put("code", "500");
                res.put("msg", "分配失败");
                return res;
            }
        }
        return res;
    }


}
