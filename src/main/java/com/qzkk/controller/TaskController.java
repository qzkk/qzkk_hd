package com.qzkk.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.qzkk.domain.Task;
import com.qzkk.service.TaskService;
import com.qzkk.vo.TeamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    @PostMapping("/getTaskListByUid")
    public JSONObject getTaskListByUid(@RequestParam long uid) {
        return taskService.getTaskListByUid(uid);
    }
    @PostMapping("/getPassedTaskListByUid")
    public JSONObject getPassedTaskListByUid(@RequestParam long uid) {
        return taskService.getPassedTaskListByUid(uid);
    }
    @PostMapping("/selectTeamNotDis")
    public JSONObject selectTeamNotDis(@RequestParam long taid,@RequestParam long uid) {
        return taskService.selectTeamNotDis(taid,uid);
    }
    @PostMapping("/deleteTask")
    public JSONObject deleteTask(@RequestParam long id) {
        return taskService.deleteTask(id);
    }
    @PostMapping("/viewTeamsByTaskId")
    public JSONObject viewTeamsByTaskId(@RequestParam long taid) {
        return taskService.viewTeamsByTaskId(taid);
    }
    @PostMapping("/aplyTask")
    public JSONObject aplyTask(Task task) {
        return taskService.aplyTask(task);
    }
    @PostMapping("/distributeTa")
    public JSONObject distributeTa(@RequestBody String teamTaskList) {

        JsonObject jsonObject = (JsonObject) new JsonParser().parse(teamTaskList);
        JsonArray jsonArray= jsonObject.getAsJsonArray("teamTaskList");
        long taid=jsonObject.get("taid").getAsLong();
        Gson gson=new Gson();
        List<TeamVO> teamVOS=new ArrayList<>();
        for (JsonElement teamTaEle:jsonArray){
            TeamVO teamVO=gson.fromJson(teamTaEle,new TypeToken<TeamVO>(){}.getType());
            teamVOS.add(teamVO);
        }

        return taskService.distributeTa(teamVOS,taid);
    }
}
