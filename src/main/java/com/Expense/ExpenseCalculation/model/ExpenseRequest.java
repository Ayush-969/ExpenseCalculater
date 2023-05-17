package com.Expense.ExpenseCalculation.model;

import java.util.*;

public class ExpenseRequest {
    private String payer;
    private double amount;
    private List<String> participants;
    private ExpenseType expenseType;
    private List<Double> splits;
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public List<String> getParticipants() {
		return participants;
	}
	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}
	public ExpenseType getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public List<Double> getSplits() {
		return splits;
	}
	public void setSplits(List<Double> splits) {
		this.splits = splits;
	}
	public Object getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

    // Constructors, getters, and setters

    // ...
}

