package com.qzkk.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.TeamRepository;
import com.qzkk.domain.Team;
import com.qzkk.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: jzc
 * @date: 19/7/2019-上午10:11
 * @description:
 */
@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public JSONObject addTeamApplication(Team team) {
        JSONObject res = new JSONObject();

        teamRepository.save(team);
        res.put("code", "200");
        res.put("msg", "submit successfully!");
        return res;
    }

    @Override
    public JSONObject examineApplication(long tid) {
        JSONObject res = new JSONObject();

        Team oldTeam = teamRepository.findByTId(tid);
        oldTeam.setState(1);
        oldTeam.setCreateTime(new Date());
        teamRepository.save(oldTeam);

        res.put("code", "200");
        res.put("msg", "examined!");
        return res;
    }

    @Override
    public JSONObject refuseApplication(long tid) {
        JSONObject res = new JSONObject();

        Team oldTeam = teamRepository.findByTId(tid);
        oldTeam.setState(-1);
        teamRepository.save(oldTeam);

        res.put("code", "200");
        res.put("msg", "refused!");
        return res;
    }

    @Override
    public JSONObject viewTeams() {
        JSONObject res = new JSONObject();
        JSONArray teamsArray = new JSONArray();

        List<Team> teams = teamRepository.findAll();
        teamsArray = (JSONArray) JSONArray.toJSON(teams);
        res.put("teams", teamsArray);
        res.put("length", teamsArray.size());
        res.put("code", "200");
        res.put("msg", "query successfully!");
        return res;
    }

    @Override
    public JSONObject delTeam(long tid) {
        JSONObject res = new JSONObject();

        Team oldTeam = teamRepository.findByTId(tid);
        oldTeam.setDel(1);
        oldTeam.setDelTime(new Date());
        teamRepository.save(oldTeam);

        res.put("code", "200");
        res.put("msg", "deleted!");
        return res;
    }

    @Override
    public JSONObject creatTeam(Team team){
        JSONObject res =new JSONObject();
        List<Team> tempTeam = null;

        //排除队伍名称相同
        tempTeam = teamRepository.findByName(team.getName());
        if(!tempTeam.isEmpty()){
            res.put("code","401");
            res.put("msg","name has exist");
            return res;
        }
        else {
            teamRepository.save(team);
            res.put("code","200");
            res.put("msg","team has creat");
            return res;
        }
    }
//测试上传

}
