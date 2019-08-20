package com.qzkk.dao;

import com.qzkk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: jzc
 * @date: 13/7/2019-下午6:43
 * @description:
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 通过账号和密码查找用户
     * @param account
     * @param psd
     * @return User
     */
    User findByAccountAndPsd(String account, String psd);

    /**
     * 通过账号查找用户
     * @param account
     * @return User
     */
    List<User> findByAccount(String account);

    /**
     * 查找未审核的用户
     * @param i: examine为0，表示未审核
     * @return User列表
     */
    List<User> findByExamineEquals(int i);

    User findByUId(long Uid);


}
