package com.qzkk.service;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Task;
import com.qzkk.vo.TeamVO;

import java.util.List;

public interface TaskService {
    JSONObject getTaskList(long teamId);
    JSONObject getTaskList1();
    JSONObject getTaskListByUid(long uid);
    JSONObject getPassedTaskListByUid(long uid);
    JSONObject selectTeamNotDis(long taskid,long uid);
    JSONObject selectChargedTeam(long uid);
    JSONObject deleteTask(long id);
    JSONObject viewTeamsByTaskId(long taid);
    JSONObject aplyTask(List<TeamVO> list,Task task);
    JSONObject distributeTa(List<TeamVO> list,long taid);


}
