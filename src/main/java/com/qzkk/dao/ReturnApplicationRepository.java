package com.qzkk.dao;

import com.qzkk.domain.Good;
import com.qzkk.domain.ReturnApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReturnApplicationRepository extends JpaRepository<ReturnApplication, Long> {
    @Query(value = "select r from ReturnApplication r where r.del=0")
    List<ReturnApplication> getReturnAply();
    /**
     * 通过GaId要归还的那条记录
     * @param gaId
     * @return GoodApplication
     */
    ReturnApplication findByGaId(long gaId);
}
