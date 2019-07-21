package com.qzkk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Team;

/**
 * @author: jzc
 * @date: 18/7/2019-下午8:58
 * @description:
 */
public interface TeamService {

    //添加队伍申请
    JSONObject addTeamApplication(Team team);

    //通过申请
    JSONObject examineApplication(long tid);

    //不通过申请
    JSONObject refuseApplication(long tid);

    //查看小队
    JSONObject viewTeams();

    //删除小队
    JSONObject delTeam(long tid);



}
