package com.pj.userguard.user.repository;

import com.pj.userguard.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
