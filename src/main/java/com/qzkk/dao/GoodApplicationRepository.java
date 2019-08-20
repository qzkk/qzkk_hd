package com.qzkk.dao;


import com.qzkk.domain.GoodApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author: jzc
 * @date: 18/7/2019-下午1:36
 * @description:
 */
public interface GoodApplicationRepository extends JpaRepository<GoodApplication, Long> {
    /**
     * 通过GaId查找物资申请信息
     * @param GaId
     * @return GoodApplication
     */
    GoodApplication findByGaId(long GaId);

    List<GoodApplication> findAllByUId(long uid);

    @Modifying
    @Transactional
    @Query("delete from GoodApplication g where g.gaId =?1")
    void deleteOne(long gaId);

    @Query(value = "select ga.ga_id,ga.g_id,ga.u_id,ga.number,ga.description,ga.state,g.name,g.identifier,te.name as tname,ta.subject_name as sn from good_application ga \n" +
            "JOIN good g on ga.g_id=g.g_id \n" +
            "join team te on ga.team_id=te.t_id\n" +
            "join task ta on ga.task_id=ta.id\n" +
            "where ga.u_id=:uid\n" +
            "order BY ga.team_id",nativeQuery = true)
    List<Object[]> getGoodAplyInfoByUid(@Param("uid") long uid);
    /**
     * 查询某小队申请成功的物资
     * @param uid 队长编号
     * @param i i=1审核通过
     * @return
     */
    List<GoodApplication> findAllByUIdAndState(long uid, int i);
}
