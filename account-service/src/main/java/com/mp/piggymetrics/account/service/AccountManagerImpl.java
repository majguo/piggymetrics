package com.mp.piggymetrics.account.service;

import com.mp.piggymetrics.account.domain.Account;
import com.mp.piggymetrics.account.domain.Currency;
import com.mp.piggymetrics.account.domain.Saving;
import com.mp.piggymetrics.account.domain.User;
import com.mp.piggymetrics.account.repository.AccountRepository;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class AccountManagerImpl implements AccountManager {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private AccountRepository repository;

    @Override
    public Account add(User user) {
    	Saving saving = new Saving();
		saving.setAmount(new BigDecimal(0));
		saving.setCurrency(Currency.getDefault());
		saving.setInterest(new BigDecimal(0));
		saving.setDeposit(false);
		saving.setCapitalization(false);

		Account account = new Account();
		account.setName(user.getUsername());
		account.setLastSeen(new Date());
		account.setSaving(saving);

		return repository.save(account);
    }

    @Override
    public Account get(String name) {
        List<Account> accounts = repository.findByName(name);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

}
