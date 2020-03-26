package com.mp.piggymetrics.auth.service;

import com.mp.piggymetrics.auth.domain.User;
import com.mp.piggymetrics.auth.repository.UserRepository;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UserManagerImpl implements UserManager {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private UserRepository repository;

    @Override
    public User add(User user) {

    	List<User> users = repository.findByUsername(user.getUsername());
		if (!users.isEmpty()) {
			return null;
		}
		
		// TODO: role should be set by account-service when creating new account
		// TODO: define role Enum to encapsulate constant roles
		user.setRole("user");
		// TODO: encode password for security
        repository.save(user);

        System.out.println(String.format("new user has been created: %s", user.getUsername()));
        return user;
    }

    @Override
    public User get(String name) {
        List<User> users = repository.findByUsername(name);
        return users.size() != 1 ? null : users.get(0);
    }

	@Override
	public List<User> getAll() {
		return repository.findAll();
	}
}
