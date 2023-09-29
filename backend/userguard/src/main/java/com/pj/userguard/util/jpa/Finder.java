package com.pj.userguard.util.jpa;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface Finder<T, ID> extends Repository<T, ID> { }
