package com.karamanmert.auth_service.repository;

import com.karamanmert.auth_service.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author karamanmert
 * @date 11.11.2024
 */
@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    @Query("SELECT au FROM AuthUser au " +
            "WHERE au.email =:username")
    Optional<AuthUser> findByUsername(@Param("username") String username);
}
