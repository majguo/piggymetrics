package com.mp.piggymetrics.account.service;

import java.util.List;

import com.mp.piggymetrics.account.domain.Todo;

public interface TodoManager {

    Todo add(Todo todo);

    Todo get(String id);

    List<Todo> getAll();

    void delete(String id);
}
