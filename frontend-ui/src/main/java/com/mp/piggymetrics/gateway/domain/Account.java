package com.mp.piggymetrics.gateway.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Account {

	private String name;
	
	private String lastSeen;
	
	@Valid
	private List<Item> incomes;
	
	@Valid
	private List<Item> expenses;
	
	@Valid
	@NotNull
	private Saving saving;
	
	@Size(min = 0, max = 20_000)
	private String note;

	public Account() {
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}

	public List<Item> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<Item> incomes) {
		this.incomes = incomes;
	}

	public List<Item> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Item> expenses) {
		this.expenses = expenses;
	}

	public Saving getSaving() {
		return saving;
	}

	public void setSaving(Saving saving) {
		this.saving = saving;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
