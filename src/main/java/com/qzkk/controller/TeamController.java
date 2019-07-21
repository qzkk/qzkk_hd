package com.qzkk.controller;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Team;
import com.qzkk.domain.User;
import com.qzkk.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getTeamList")
    public JSONObject getTeamList() {
        return teamService.viewTeams();
    }

    @PostMapping("/examineTeamApplication")
    public JSONObject examineApplication(@RequestParam long tid) {
        return teamService.examineApplication(tid);
    }

    @PostMapping("/refuseTeamApplication")
    public JSONObject refuseApplication(@RequestParam long tid) {
        return teamService.refuseApplication(tid);
    }


}
