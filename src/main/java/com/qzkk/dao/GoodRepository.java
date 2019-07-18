package com.qzkk.dao;

import com.qzkk.domain.Good;
import com.qzkk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: jzc
 * @date: 16/7/2019-下午1:42
 * @description:
 */
public interface GoodRepository extends JpaRepository<Good, Long> {

    //查询所有记录
    List<Good> findAll();

    //根据gid查询物资
    Good findByGId(long Gid);

    //根据Identifier查询物资
    Good findByIdentifier(String identifier);

}
