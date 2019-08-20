package com.qzkk.dao;

import com.qzkk.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "select ta.id,ta.subject_name as sn from task ta join team_task tt on tt.task_id=ta.id where tt.team_id=:teamId",nativeQuery = true)
    List<Object[]> getTaskListByTeamId(@Param("teamId") long teamId);
}
