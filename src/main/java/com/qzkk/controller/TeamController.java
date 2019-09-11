package com.qzkk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.qzkk.domain.Team;
import com.qzkk.domain.User;
import com.qzkk.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: jzc
 * @date: 19/7/2019-上午10:53
 * @description:
 */
@RestController
public class TeamController {
    @Autowired
    private TeamService teamService;

    /**
     * 添加队伍申请（暂时废弃此功能）
     * @param team
     * @return
     */
    @PostMapping("/addTeamApplication")
    public JSONObject addTeamApplication(Team team) {
        return teamService.addTeamApplication(team);
    }

    /**
     * 获得该负责人名下的小队列表
     * @param uid
     * @return
     */
    @PostMapping("/getTeamList")
    public JSONObject getTeamList(@RequestParam long uid) {
        return teamService.viewTeams(uid);
    }

    /**
     * 获得该队长审核已通过的小队
     * @param uid
     * @return
     */
    @PostMapping("/teamListOfAccessByUid")
    public JSONObject teamListOfAccessByUid(@RequestParam long uid) {
        return teamService.teamListOfAccessByUid(uid);
    }

//    @PostMapping("/teamListOfAccessByUid")
//    public JSONObject teamListOfAccessByUid(@RequestParam long uid) {
//        return teamService.teamListOfAccessByUid(uid);
//    }

    /**
     * 把成员添加至该小队
     * @param userList
     * @return
     */
    @PostMapping("/addUserToTeam")
    public JSONObject addUserToTeam(@RequestBody String userList) {
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(userList);
        JsonArray jsonArray= jsonObject.getAsJsonArray("userList");
        Gson gson=new Gson();
        List<User> userList1=new ArrayList<>();
        for (JsonElement userElemet:jsonArray){
            User user=gson.fromJson(userElemet,new TypeToken<User>(){}.getType());
            userList1.add(user);
        }

        return teamService.addUserToTeam(userList1);
    }

    /**
     * 通过其小队申请（暂时废弃此功能）
     * @param tid 小队id
     * @return
     */
    @PostMapping("/examineTeamApplication")
    public JSONObject examineApplication(@RequestParam long tid) {
        return teamService.examineApplication(tid);
    }

    /**
     * 拒绝其小队申请（暂时废弃此功能）
     * @param tid 小队id
     * @return
     */
    @PostMapping("/refuseTeamApplication")
    public JSONObject refuseApplication(@RequestParam long tid) {
        return teamService.refuseApplication(tid);
    }

    /**
     * 获得成员以及所属小队的列表
     * @param uid
     * @return
     */
    @PostMapping("/getMemberAboutTeam")
    public JSONObject getMemberAboutTeam(@RequestParam long uid) {
        return teamService.getMemberAboutTeam(uid);
    }

    /**
     * 把该队员从该小队里删除
     * @param id
     * @return
     */
    @PostMapping("/delTeamMember")
    public JSONObject delTeamMember(@RequestParam long id) {
        return teamService.delTeamMember(id);
    }

    /**
     * 获取该负责人下的小队信息（暂时不用）
     * @param uid
     * @return
     */
    @PostMapping("/getTeamInfo")
    public JSONObject getTeamInfo(@RequestParam long uid) {
        return teamService.viewTeamInfo(uid);
    }

    /**
     * 获取审核通过的小队
     * @param state
     * @return
     */
    @PostMapping("/getTeamLists")
    public JSONObject getTeamLists(@RequestParam int state){
        return teamService.getTeamLists(state);
    }

    /**
     * 通过团队id查看团队成员
     * @param tId 团队id
     * @return
     */
    @PostMapping("/teamUserList")
    public JSONObject teamUserList(BigInteger tId){
        return teamService.teamUserList(tId);
    }

    /**
     * 小队审批
     * @param tId
     * @param state
     * @return
     */
    @PostMapping("/exTeamApplication")
    public JSONObject examineTeamApplication(@RequestParam long tId ,@RequestParam int state){
        return teamService.examineTeamApplication(tId,state);
    }



}
