package com.qzkk.dao;

import com.qzkk.domain.Good;
import com.qzkk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

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

    //返回还有剩余的物资信息
    //@Query(value = "select new map(u.userName, ui.name, ui.gender, ui.description) from UserInfo ui, User u where u.id = ui.userId")
    @Query(value = "select g from Good g where g.number-g.usingNumber>0")
    List<Good> getLeftGoodTypes();


}
