package com.qzkk.dao;

import com.qzkk.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM User WHERE del=0 and examine=1 ORDER BY u_id desc "
            , countQuery = "SELECT count(*) FROM User WHERE del=0 and examine=1"
            , nativeQuery = true)
    Page<User> findToPage(String name, Pageable pageable);

    @Query(value = "select u.* from user u where u.u_id not in (select tu.user_id from team_user tu where tu.team_id=:tid) and u.examine=1"
            , countQuery = "select COUNT(1) from user u where u.u_id not in (select tu.user_id from team_user tu where tu.team_id=:tid) and u.examine=1"
            , nativeQuery = true)
    Page<User> findToPage1(@Param("tid")long tid, Pageable pageable);

    @Query(value = "select u.* from user u where u.u_id not in (select tu.user_id from team_user tu where tu.team_id=:tid) and u.name like %:name% and u.examine=1 "
            , countQuery = "select COUNT(1) from user u where u.u_id not in (select tu.user_id from team_user tu where tu.team_id=:tid) and u.name like %:name% and u.examine=1"
            , nativeQuery = true)
    Page<User> selectToPageByDynamic1(@Param("tid")long tid,@Param("name")String name, Pageable pageable);
}


