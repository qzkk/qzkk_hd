package com.qzkk.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Team;
import com.qzkk.domain.User;

import java.math.BigInteger;
import java.util.List;

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
    JSONObject viewTeams(long uid);
    JSONObject teamListOfAccessByUid(long uid);

    //删除小队
    JSONObject delTeam(long tid);

    //创建小队
    JSONObject creatTeam(Team team);

    JSONObject addUserToTeam(List<User> users);

    JSONObject getMemberAboutTeam(long uid);

    JSONObject delTeamMember(long uid);

    //查看小队信息
    JSONObject viewTeamInfo(long uid);

    JSONObject getTeamLists(int state);

    JSONObject teamUserList(BigInteger tId);

    JSONObject examineTeamApplication(Long tId , int state);


}
