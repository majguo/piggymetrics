package com.mp.piggymetrics.account.repository;

import org.jnosql.artemis.Repository;

import com.mp.piggymetrics.account.domain.Account;

import java.util.List;

public interface AccountRepository extends Repository<Account, String> {
    List<Account> findByName(String name);
    List<Account> findAll();
}
