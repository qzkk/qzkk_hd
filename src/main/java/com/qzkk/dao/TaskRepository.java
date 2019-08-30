package com.qzkk.dao;

import com.qzkk.domain.Task;
import com.qzkk.domain.Team;
import com.qzkk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "select ta.id,ta.subject_name as sn from task ta join team_task tt on tt.task_id=ta.id where tt.team_id=:teamId",nativeQuery = true)
    List<Object[]> getTaskListByTeamId(@Param("teamId") long teamId);

    @Query(value = "select ta.id,ta.demand,ta.research_site as rs,ta.subject_name as sn,ta.subject_task as st,ta.start_date as sd,ta.end_date as ed,u.name,u.type,ta.state from task ta join user u on ta.uid=u.u_id where u.u_id=:uid",nativeQuery = true)
    List<Object[]> getTaskListByUid(@Param("uid") long uid);



    @Query(value = "select ta.id,ta.demand,ta.research_site as rs,ta.subject_name as sn,ta.subject_task as st,ta.start_date as sd,ta.end_date as ed,u.name,u.type,ta.state from task ta join user u on ta.uid=u.u_id where ta.state=0",nativeQuery = true)
    List<Object[]> getTaskListByExamine();

    @Query(value = "select ta.id,ta.demand,ta.research_site as rs,ta.subject_name as sn,ta.subject_task as st,u.name,u.type,ta.state from task ta join user u on ta.uid=u.u_id where ta.state=1 and u.u_id=:uid",nativeQuery = true)
    List<Object[]> getPassedTaskListByUid(@Param("uid") long uid);

    @Query(value = "select te.t_id as tid,te.u_id as uid,te.name as tname,u.name as uname from team_task tt \n" +
            "join team te on te.t_id=tt.team_id \n" +
            "join task ta on tt.task_id=ta.id\n" +
            "join user u on te.u_id=u.u_id\n" +
            "where te.del=0 and ta.id=:taid",nativeQuery = true)
    List<Object[]> viewTeamsByTaskId(@Param("taid") long taid);
    @Query(value = "select te.t_id as tid,te.name,u.name as uname,te.state from team te join user u on te.u_id=u.u_id where te.state=1 and te.u_id=:uid and te.t_id not in(select tt.team_id from team_task tt where tt.task_id=:taid)",nativeQuery = true)
    List<Object[]> selectTeamNotDis(@Param("taid") long taid, @Param("uid") long uid);
    @Query(value = "select te.t_id as tid,te.name,u.name as uname,te.state from team te \n" +
            "join user u on te.u_id=u.u_id \n" +
            "where te.state=1 and te.u_id=:uid",nativeQuery = true)
    List<Object[]> selectChargedTeam(@Param("uid") long uid);
    @Query(value = "select * from task t order by t.id desc limit 1",nativeQuery = true)
    Task selectNewTask();

    @Query(value = "select u.name,u.type,u.sex,u.id_card as idCard,u.work_position as wp,u.work_unit as wu from user u join team_user tu on u.u_id=tu.user_id where tu.team_id=:tid order by type asc",nativeQuery = true)
    List<Object[]> viewMemeberByTid(@Param("tid") long tid);

    @Query(value = "select ta.id,ta.demand,ta.research_site as rs,ta.subject_name as sn,ta.subject_task as st,ta.start_date as sd,ta.end_date as ed,u.name,u.type,ta.state from task ta join user u on ta.uid=u.u_id and ta.state=1 where u.u_id=:uid",nativeQuery = true)
    List<Object[]> getTaskListOfAccessByUid(@Param("uid") long uid);

//    @Query(value = "select ta.id,ta.demand,ta.research_site as rs,ta.subject_name as sn,ta.subject_task as st,ta.start_date as sd,ta.end_date as ed,u.name,u.type,ta.state from task ta join user u on ta.uid=u.u_id where ta.state=1 and u.u_id=:uid",nativeQuery = true)
//    List<Object[]> getTaskListOfAccessByUid(@Param("uid") long uid);

}
