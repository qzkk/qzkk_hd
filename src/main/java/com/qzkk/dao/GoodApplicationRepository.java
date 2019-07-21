package com.qzkk.dao;


import com.qzkk.domain.GoodApplication;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
