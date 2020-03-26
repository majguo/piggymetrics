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
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
public class AccountManagerImpl implements AccountManager {

    @Inject
    @Database(DatabaseType.DOCUMENT)
    private AccountRepository repository;

    @Override
    public Account add(User user) {
        //TODO: Call auth-service to create user

    	Saving saving = new Saving();
		saving.setAmount(new BigDecimal(0));
		saving.setCurrency(Currency.getDefault());
		saving.setInterest(new BigDecimal(0));
		saving.setDeposit(false);
		saving.setCapitalization(false);

		Account account = new Account();
		account.setName(user.getUsername());
		account.setLastSeen(ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT));
        account.setSaving(saving);
        
        repository.save(account);

        System.out.println(String.format("new account has been created: %s", account.getName()));

		return account;
    }

    @Override
    public void save(String name, Account update) {
        List<Account> accounts = repository.findByName(name);
		if (accounts.size() != 1) {
			return;
		}
		
		Account account = accounts.get(0);
		account.setIncomes(update.getIncomes());
		account.setExpenses(update.getExpenses());
		account.setSaving(update.getSaving());
		account.setNote(update.getNote());
		account.setLastSeen(ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT));
		repository.save(account);

		System.out.println(String.format("account %s changes has been saved\n", name));

		//TODO: Call statistics-service to update user's statistics
    }

    @Override
    public Account get(String name) {
        List<Account> accounts = repository.findByName(name);
        return accounts.size() != 1 ? null : accounts.get(0);
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
