package com.qzkk.dao;

import com.qzkk.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM Registration WHERE name = ?1"
            , countQuery = "SELECT count(*) FROM User WHERE name = ?1"
            , nativeQuery = true)
    Page<User> findToPage(String name, Pageable pageable);
}


