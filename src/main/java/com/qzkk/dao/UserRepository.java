package com.qzkk.dao;

import com.qzkk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

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


}
