package com.pj.userguard.user.repository;

import com.pj.userguard.user.entity.User;
import com.pj.userguard.user.field.EmailAddress;
import com.pj.userguard.user.field.Username;
import com.pj.userguard.util.jpa.Finder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserFinder extends Finder<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.username = :username")
    Optional<Username> findByUsername(@Param("username") Username username);

    @Query(value = "SELECT u FROM User u WHERE u.emailAddress = :emailAddress")
    Optional<EmailAddress> findByEmailAddress(@Param("emailAddress") EmailAddress emailAddress);
}
