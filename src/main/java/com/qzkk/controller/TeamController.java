package com.qzkk.controller;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Team;
import com.qzkk.domain.User;
import com.qzkk.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addUserToTeam")
    public JSONObject addUserToTeam(@RequestBody User[] users) {
        System.out.println();
        return null;
        //return teamService.addUserToTeam(users);
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
    public JSONObject delTeamMember(@RequestParam long uid) {
        return teamService.delTeamMember(uid);
    }

    @PostMapping("/getTeamInfo")
    public JSONObject getTeamInfo(@RequestParam long uid) {
        return teamService.viewTeamInfo(uid);
    }



}
