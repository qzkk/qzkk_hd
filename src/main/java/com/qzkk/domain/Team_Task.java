package com.qzkk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 小队任务关联表
 */
@Entity
@Table(name="team_task")
@Data
@NoArgsConstructor
public class Team_Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //任务id
    private long taskId;

    //小队id
    private long teamId;


}
