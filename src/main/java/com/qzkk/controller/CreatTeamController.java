package com.qzkk.controller;


import com.alibaba.fastjson.JSONObject;
import com.qzkk.domain.Team;
import com.qzkk.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hjh
 * @date: 5/8/2019-上午9:00
 * @description:
 */
@RestController
public class CreatTeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping("/creatTeam")
    public JSONObject creatTeam(Team team){
        return teamService.creatTeam(team);
    }

}
