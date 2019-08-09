package com.qzkk.dao;


import com.qzkk.domain.GoodApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
}
