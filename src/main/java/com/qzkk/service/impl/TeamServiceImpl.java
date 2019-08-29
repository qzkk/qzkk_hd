package com.qzkk.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.*;
import com.qzkk.domain.*;
import com.qzkk.service.TeamService;
import com.qzkk.utils.CastEntity;
//import com.qzkk.vo.TeamList;
import com.qzkk.vo.TeamInfoVO;
import com.qzkk.vo.TeamList;
import com.qzkk.vo.TeamMember;
//import com.qzkk.vo.TeamUserInfo;
import com.qzkk.vo.TeamUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GoodApplicationRepository goodApplicationRepository;
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private TeamUserRespository teamUserRespository;

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
    public JSONObject viewTeams(long uid) {
        JSONObject res = new JSONObject();
        try {
            List<Team> list = teamRepository.findByUId(uid);
            res.put("list", list);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg", "查询小队失败");
            res.put("code", "500");
        }

        return res;
    }

    @Override
    public JSONObject teamListOfAccessByUid(long uid) {
        JSONObject res=new JSONObject();
        try {
            List<Object[]> list=teamRepository.teamListOfAccessByUid(uid);
            List<TeamInfoVO> teams = CastEntity.castEntity(list, TeamInfoVO.class);
            res.put("code", "200");
            res.put("list", teams);
            res.put("msg", "查询成功");
        } catch (Exception e) {
            res.put("code", "500");
            res.put("msg", "查询失败");
        }
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
            team.setState(0);//待审核状态
            teamRepository.save(team);
            res.put("code","200");
            res.put("msg","team has creat");
            return res;
        }
    }

    @Override
    public JSONObject addUserToTeam(List<User> users) {
        JSONObject res = new JSONObject();
        for (int i=0;i<users.size();i++){
            Team_User team_user=new Team_User();
            team_user.setTeamId(users.get(i).getTId());
            team_user.setUserId(users.get(i).getUId());
            try {
                teamUserRespository.save(team_user);
                res.put("code", "200");
                res.put("msg", "添加成功");
            }catch (Exception e){
                res.put("code", "500");
                res.put("msg", "添加失败");
                return res;
            }
        }

        return res;
    }

    @Override
    public JSONObject getMemberAboutTeam(long uid) {
        JSONObject res=new JSONObject();
        try {
            List<Object[]> list=teamRepository.getMemberAboutTeam(uid);
            List<TeamMember> teamMembers = CastEntity.castEntity(list, TeamMember.class);
            res.put("code", "200");
            res.put("list", teamMembers);
            res.put("msg", "查询成功");
        } catch (Exception e) {
            res.put("code", "500");
            res.put("msg", "查询失败");
        }
        return res;
    }

    @Override
    public JSONObject delTeamMember(long uid) {
        JSONObject res=new JSONObject();
        //oldUser.setTId(0);

        try {
            teamUserRespository.deleteOne(uid);
            res.put("code", "200");
            res.put("msg", "删除成功");
        }catch (Exception e){
            res.put("code", "200");
            res.put("msg", "删除失败");
        }
        return res;
    }

    //查看小队信息
    @Override
    public JSONObject viewTeamInfo(long uid) {
        JSONObject res=new JSONObject();
        JSONArray membersArray = new JSONArray();
        JSONArray goodsInfoArray = new JSONArray();

        User user = userRepository.findByUId(uid);
        //long tId = user.getTId();
       // Team team = teamRepository.findByTId(tId);
//        res.put("TeamName", team.getName());
//        res.put("Task", team.getKkTask());
//        res.put("Site", team.getResearchSite());
//        res.put("Content", team.getSubjectContent());

      //  long captionId = team.getUId();
       // User caption = userRepository.findByUId(captionId);
        //List<User> members = userRepository.findByTId(tId);
//        for (User m:members) {
//            membersArray.add(m.getName());
//        }
        res.put("members", membersArray);

        //List<GoodApplication> goodApplications = goodApplicationRepository.findAllByUIdAndState(captionId, 1);
//        for(GoodApplication g : goodApplications ) {
//            JSONObject temp = new JSONObject();
//            Good tempGood = goodRepository.findByGId(g.getGId());
//            temp.put("name", tempGood.getName());
//            temp.put("number", g.getNumber());
//            temp.put("specification", tempGood.getSpecification());
//            goodsInfoArray.add(temp);
//        }
//        res.put("goods", goodsInfoArray);
//        res.put("code", "200");
        return res;
    }

    @Override
    public JSONObject getTeamLists(int state){
        JSONObject res =new JSONObject();
        try{
            List<Object[]> team =teamRepository.getTeamLists(state);
            List<TeamList> teamList = CastEntity.castEntity(team,TeamList.class);
            res.put("data",teamList);
            res.put("code","200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code","500");
        }
        return res;
    }
//测试上传

    @Override
    public JSONObject teamUserList(BigInteger tId){
        JSONObject res =new JSONObject();
        try {
            List<Object[]> team = teamRepository.teamUserList(tId);
            List<TeamUserInfo> teamList = CastEntity.castEntity(team, TeamUserInfo.class);
            res.put("data",teamList);
            res.put("code","200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code","500");
        }
        return res;
    }

    @Override
    public JSONObject examineTeamApplication(Long tId ,int state){
        JSONObject res = new JSONObject();
        Team team = teamRepository.findByTId(tId);
        team.setState(state);
        teamRepository.save(team);
        res.put("code","200");
        res.put("msg","修改完成");
        return res;
    }

}
