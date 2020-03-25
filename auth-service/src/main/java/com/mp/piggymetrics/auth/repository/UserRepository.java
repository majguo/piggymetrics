package com.mp.piggymetrics.auth.repository;

import org.jnosql.artemis.Repository;

import com.mp.piggymetrics.auth.domain.User;

import java.util.List;

public interface UserRepository extends Repository<User, String> {
    List<User> findByUsername(String name);
}
