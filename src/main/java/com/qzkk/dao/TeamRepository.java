package com.qzkk.dao;

import com.qzkk.domain.Team;
import com.qzkk.domain.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: jzc
 * @date: 19/7/2019-上午10:12
 * @description:
 */

public interface TeamRepository extends JpaRepository<Team, Long> {

    /**
     * 查询所有小队的信息
     * @return
     */
    List<Team> findAll();

    /**
     * 通过tid查找小队
     * @param Tid
     * @return
     */
    Team findByTId(long Tid);

    /**
     * 通过名称查找小队
     * @param name
     * @return
     */
    List<Team> findByName(String name);

    List<Team> findByUId(long Uid);

    @Query(value = "select u.u_id,u.name,t.name as tname from user u join team t on t.t_id=u.t_id WHERE t.u_id=:uid",nativeQuery = true)
    List<Object[]> getMemberAboutTeam(@Param("uid") long uid);



}
