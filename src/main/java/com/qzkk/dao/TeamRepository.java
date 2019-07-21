package com.qzkk.dao;

import com.qzkk.domain.Team;
import com.qzkk.domain.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

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



}
