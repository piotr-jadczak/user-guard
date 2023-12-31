package com.pj.userguard.client.repository;

import com.pj.userguard.client.entity.Client;
import com.pj.userguard.client.field.ClientId;
import com.pj.userguard.client.field.UniqueId;
import com.pj.userguard.util.jpa.Finder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientFinder extends Finder<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.uniqueId = :uniqueId")
    Optional<Client> findByUniqueId(@Param("uniqueId") UniqueId uniqueId);

    @Query("SELECT c FROM Client c WHERE c.clientId = :clientId")
    Optional<Client> findByClientId(@Param("clientId") ClientId clientId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE c.uniqueId = :uniqueId")
    boolean existsByUniqueId(@Param("uniqueId") UniqueId uniqueId);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Client c WHERE c.clientId = :clientId")
    boolean existsByClientId(@Param("clientId") ClientId clientId);
}
