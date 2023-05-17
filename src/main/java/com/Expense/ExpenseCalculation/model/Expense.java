package com.Expense.ExpenseCalculation.model;

import java.util.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

@EntityScan
public class Expense {
    private static final String GenerationType = null;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private double amount;
    private String payer;

    @ManyToMany
    private List<User> users;

    private ExpenseType expenseType;
    private List<Double> splits;
	public void setAmount(double amount2) {
		// TODO Auto-generated method stub
		
	}
	public void setPayer(String payer2) {
		// TODO Auto-generated method stub
		
	}
	public void setUsers(List<User> users2) {
		// TODO Auto-generated method stub
		
	}
	public void setDescription(Object description2) {
		// TODO Auto-generated method stub
		
	}
	public void setExpenseType(ExpenseType expenseType2) {
		// TODO Auto-generated method stub
		
	}
	public void setSplits(List<Double> splits2) {
		// TODO Auto-generated method stub
		
	}
	public Object getPayer() {
		// TODO Auto-generated method stub
		return null;
	}
	public double getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<User> getSplits() {
		// TODO Auto-generated method stub
		return null;
	}
	public ExpenseType getExpenseType() {
		// TODO Auto-generated method stub
		return null;
	}
}


