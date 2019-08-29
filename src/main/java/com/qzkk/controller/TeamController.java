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

    @PostMapping("/addTeamApplication")
    public JSONObject addTeamApplication(Team team) {
        return teamService.addTeamApplication(team);
    }

    @PostMapping("/getTeamList")
    public JSONObject getTeamList(@RequestParam long uid) {
        return teamService.viewTeams(uid);
    }
    @PostMapping("/teamListOfAccessByUid")
    public JSONObject teamListOfAccessByUid(@RequestParam long uid) {
        return teamService.teamListOfAccessByUid(uid);
    }

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

    @PostMapping("/examineTeamApplication")
    public JSONObject examineApplication(@RequestParam long tid) {
        return teamService.examineApplication(tid);
    }

    @PostMapping("/refuseTeamApplication")
    public JSONObject refuseApplication(@RequestParam long tid) {
        return teamService.refuseApplication(tid);
    }

    @PostMapping("/getMemberAboutTeam")
    public JSONObject getMemberAboutTeam(@RequestParam long uid) {
        return teamService.getMemberAboutTeam(uid);
    }

    @PostMapping("/delTeamMember")
    public JSONObject delTeamMember(@RequestParam long id) {
        return teamService.delTeamMember(id);
    }

    @PostMapping("/getTeamInfo")
    public JSONObject getTeamInfo(@RequestParam long uid) {
        return teamService.viewTeamInfo(uid);
    }

    @PostMapping("/getTeamLists")
    public JSONObject getTeamLists(@RequestParam int state){
        return teamService.getTeamLists(state);
    }

    @PostMapping("/teamUserList")
    public JSONObject teamUserList(BigInteger tId){
        return teamService.teamUserList(tId);
    }

    @PostMapping("/exTeamApplication")
    public JSONObject examineTeamApplication(@RequestParam long tId ,@RequestParam int state){
        return teamService.examineTeamApplication(tId,state);
    }



}
