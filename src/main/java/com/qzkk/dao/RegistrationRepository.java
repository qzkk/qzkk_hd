package com.qzkk.dao;

import com.qzkk.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM User WHERE del=0 and examine=1 ORDER BY u_id desc "
            , countQuery = "SELECT count(*) FROM User WHERE del=0 and examine=1"
            , nativeQuery = true)
    Page<User> findToPage(String name, Pageable pageable);
}


