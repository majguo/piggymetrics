package com.mp.piggymetrics.account.service;

import java.util.List;

import com.mp.piggymetrics.account.domain.Account;
import com.mp.piggymetrics.account.domain.User;

public interface AccountManager {

	Account add(User user);

	Account get(String name);

    List<Account> getAll();

    void delete(String id);
}
