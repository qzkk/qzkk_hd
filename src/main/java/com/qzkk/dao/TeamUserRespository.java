package com.qzkk.dao;

import com.qzkk.domain.Team_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TeamUserRespository extends JpaRepository<Team_User, Long> {
    @Modifying
    @Transactional
    @Query("delete from Team_User g where g.id =?1")
    void deleteOne(long id);
}
