package com.qzkk.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 小队用户关联表
 */
@Entity
@Table(name="team_user")
@Data
@NoArgsConstructor
public class Team_User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //任务id
    private long userId;

    //小队id
    private long teamId;

	public void setTeamId(long tId) {
        this.teamId = tId; 
	}

	public void setUserId(long uId) {
        this.userId = uId;
	}
}
