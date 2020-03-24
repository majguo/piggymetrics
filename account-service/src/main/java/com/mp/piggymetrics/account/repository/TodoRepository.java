package com.mp.piggymetrics.account.repository;

import org.jnosql.artemis.Repository;

import com.mp.piggymetrics.account.domain.Todo;

import java.util.List;

public interface TodoRepository extends Repository<Todo, String> {
    List<Todo> findByName(String name);
    List<Todo> findAll();
}
