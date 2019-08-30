package com.qzkk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qzkk.dao.TaskRepository;
import com.qzkk.dao.TeamTaskRepository;
import com.qzkk.dao.UserRepository;
import com.qzkk.domain.*;
import com.qzkk.service.TaskService;
import com.qzkk.utils.CastEntity;
import com.qzkk.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TeamTaskRepository teamTaskRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public JSONObject getTaskList(long teamId) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.getTaskListByTeamId(teamId);
            List<TaskVO> taskVOS = CastEntity.castEntity(list, TaskVO.class);//类已经改了，不好用了，到时候看情况再改

            res.put("list",taskVOS);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject getTaskList1() {
        JSONObject res = new JSONObject();
        try{
            List<Task> list=taskRepository.findAll();
            res.put("list",list);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject getTaskListByUid(long uid) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.getTaskListByUid(uid);
            List<TaskVO> taskVOS = CastEntity.castEntity(list, TaskVO.class);
            res.put("list",taskVOS);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }
    @Override
    public JSONObject getTaskListOfAccessByUid(long uid) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.getTaskListOfAccessByUid(uid);
            List<TaskVO> taskVOS = CastEntity.castEntity(list, TaskVO.class);
            res.put("list",taskVOS);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }

//    @Override
//    public JSONObject getTaskListOfAccessByUid(long uid) {
//        JSONObject res =new JSONObject();
//        try{
//            List<Object[]> list = taskRepository.getTaskListOfAccessByUid(uid);
//            List<TaskVO> userList = CastEntity.castEntity(list, TaskVO.class);
//            res.put("code", "200");
//            res.put("list", userList);
//        }catch (Exception e){
//            res.put("code", "500");
//            res.put("msg", "操作失败");
//        }
//        return res;
//    }

    @Override
    public JSONObject getTaskListByExamine() {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.getTaskListByExamine();
            List<TaskVO> taskVOS = CastEntity.castEntity(list, TaskVO.class);
            res.put("list",taskVOS);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject getPassedTaskListByUid(long uid) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.getPassedTaskListByUid(uid);
            List<TaskVO> taskVOS = CastEntity.castEntity(list, TaskVO.class);
            res.put("list",taskVOS);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject selectTeamNotDis(long taskid, long uid) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.selectTeamNotDis(taskid,uid);
            List<TeamVO> teamVOS = CastEntity.castEntity(list, TeamVO.class);
            res.put("list",teamVOS);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject selectChargedTeam(long uid) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.selectChargedTeam(uid);
            List<TeamVO> teamVOS = CastEntity.castEntity(list, TeamVO.class);
            res.put("list",teamVOS);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject deleteTask(long id) {
        JSONObject res = new JSONObject();
        try {
            taskRepository.deleteById(id);
            res.put("msg","操作成功");
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","操作失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject viewTeamsByTaskId(long taid) {
        JSONObject res = new JSONObject();
        try{
            List<Object[]> list = taskRepository.viewTeamsByTaskId(taid);
            List<TeamCaptain> teamCaptains = CastEntity.castEntity(list, TeamCaptain.class);
            res.put("list",teamCaptains);
            res.put("code", "200");
        }catch (Exception e){
            res.put("msg","查询失败");
            res.put("code", "500");
        }
        return res;
    }

    @Override
    public JSONObject aplyTask(List<TeamVO> list,Task task) {
        JSONObject res =new JSONObject();
        try {
            if (list.size()==0){
                res.put("code", "501");
                res.put("msg", "未选择负责小队，请选择！");
                return res;
            }
            taskRepository.save(task);
            Task taskNewly=taskRepository.selectNewTask();
            for (int i=0;i<list.size();i++){
                Team_Task team_task=new Team_Task();
                team_task.setTaskId(taskNewly.getId());
                team_task.setTeamId(list.get(i).getTid().longValue());
                try {
                    teamTaskRepository.save(team_task);
                }catch (Exception e){
                    taskRepository.deleteById(taskNewly.getId());
                    res.put("code", "500");
                    res.put("msg", "申请失败，请重新尝试");
                    return res;
                }
            }
            res.put("msg","申请成功");
            res.put("code", "200");

        }catch (Exception e){
            res.put("msg","申请失败，请重新尝试");
            res.put("code", "200");

        }
        return res;
    }

    @Override
    public JSONObject distributeTa(List<TeamVO> list,long taid) {
        JSONObject res =new JSONObject();
        for (int i=0;i<list.size();i++){
            Team_Task team_task=new Team_Task();
            team_task.setTaskId(taid);
            team_task.setTeamId(list.get(i).getTid().longValue());
            try {
                teamTaskRepository.save(team_task);
                res.put("code", "200");
                res.put("msg", "分配成功");
            }catch (Exception e){
                res.put("code", "500");
                res.put("msg", "分配失败");
                return res;
            }
        }
        return res;
    }

    @Override
    public JSONObject operateTask(long taid, int state) {
        JSONObject res =new JSONObject();
        try{
            Task task=taskRepository.findById(taid).get();
            task.setState(state);
            taskRepository.save(task);
            res.put("code", "200");
            res.put("msg", "操作成功");
        }catch (Exception e){
            res.put("code", "500");
            res.put("msg", "操作失败");
        }
        return res;
    }

    @Override
    public JSONObject viewMemeberByTid(long tid) {
        JSONObject res =new JSONObject();
        try{
            List<Object[]> list = taskRepository.viewMemeberByTid(tid);
            List<UserVO> userList = CastEntity.castEntity(list, UserVO.class);
            res.put("code", "200");
            res.put("list", userList);
        }catch (Exception e){
            res.put("code", "500");
            res.put("msg", "操作失败");
        }
        return res;
    }

    @Override
    public JSONObject getTaskListToPage(SelectTaskCondition stc) {
        JSONObject resData=new JSONObject();
        try {
            Pageable pageable = PageRequest.of(stc.getPageOffset(),stc.getPageSize(),
                    Sort.Direction.DESC, "id");
            StringBuffer dataSql = new StringBuffer("select ta.id,ta.demand,ta.research_site rs,ta.subject_name sn,ta.subject_task st,ta.start_date sd,ta.end_date ed,u.name,u.type,ta.state from task ta join user u on u.u_id=ta.uid where 1=1");
            StringBuffer countSql = new StringBuffer("select count(1) from task ta join user u on u.u_id=ta.uid where 1=1");
            if (!(stc.getUname().equals(""))){
                dataSql.append(" and u.name like CONCAT('%',:uname,'%')");
                countSql.append(" and u.name like CONCAT('%',:uname,'%')");
            }
            if (!(stc.getTname().equals(""))){
                dataSql.append(" and ta.subject_name like CONCAT('%',:tname,'%')");
                countSql.append(" and ta.subject_name like CONCAT('%',:tname,'%')");
            }
            if (!(stc.getState().equals("2"))){
                dataSql.append(" and ta.state =:state");
                countSql.append(" and ta.state =:state");
            }

            Query dataQuery = (Query) entityManager.createNativeQuery(dataSql.toString(), TaskForPageVO.class);
            //查询总共有多少条数据，用于前端分页
            Query countQuery = entityManager.createNativeQuery(countSql.toString());

            //设置参数
            if (!stc.getUname().isEmpty()) {
                dataQuery.setParameter("uname", stc.getUname());
                countQuery.setParameter("uname", stc.getUname());
            }
            if (!stc.getTname().isEmpty()) {
                dataQuery.setParameter("tname", stc.getTname());
                countQuery.setParameter("tname", stc.getTname());
            }
            if (!(stc.getState().equals("2"))) {
                int state= Integer.parseInt(stc.getState());
                dataQuery.setParameter("state", state);
                countQuery.setParameter("state", state);
            }
            dataQuery.setFirstResult((int) pageable.getOffset());
            dataQuery.setMaxResults(pageable.getPageSize());
            BigInteger count = (BigInteger) countQuery.getSingleResult();
            Long totalNum = count.longValue();
            List<TaskForPageVO> list=dataQuery.getResultList();
            resData.put("totalNum",totalNum);
            resData.put("list",list);
            resData.put("code","200");
        }catch (Exception e){
            resData.put("code","500");
            resData.put("msg","查询失败");
        }
        return resData;
    }


}
