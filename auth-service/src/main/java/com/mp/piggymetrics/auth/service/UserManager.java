package com.mp.piggymetrics.auth.service;

import com.mp.piggymetrics.auth.domain.User;

public interface UserManager {

    User add(User user);

	User get(String name);
}
