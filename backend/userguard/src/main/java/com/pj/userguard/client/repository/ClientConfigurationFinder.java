package com.pj.userguard.client.repository;

import com.pj.userguard.client.entity.ClientConfiguration;
import com.pj.userguard.util.jpa.Finder;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientConfigurationFinder extends Finder<ClientConfiguration, Long> {

    @Query("SELECT cc FROM ClientConfiguration cc WHERE cc.isDefault = true")
    Optional<ClientConfiguration> findDefaultConfiguration();
}
