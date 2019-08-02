package com.qzkk.dao;

import com.qzkk.domain.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    @Query(value = "SELECT * FROM Registration WHERE name = ?1"
            , countQuery = "SELECT count(*) FROM Registration WHERE name = ?1"
            , nativeQuery = true)
    Page<Registration> findToPage(String name, Pageable pageable);
}


