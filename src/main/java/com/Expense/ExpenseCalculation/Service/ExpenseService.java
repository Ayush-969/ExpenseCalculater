package com.Expense.ExpenseCalculation.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Expense.ExpenseCalculation.Repository.ExpenseRepository;
import com.Expense.ExpenseCalculation.Repository.UserRepository;
import com.Expense.ExpenseCalculation.model.Expense;
import com.Expense.ExpenseCalculation.model.ExpenseRequest;
import com.Expense.ExpenseCalculation.model.ExpenseType;
import com.Expense.ExpenseCalculation.model.User;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public Expense createExpense(ExpenseRequest expenseRequest) {
        Expense expense = new Expense();
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.getAmount());
        expense.setPayer(expenseRequest.getPayer());
        expense.setUsers(getUsers(expenseRequest.getParticipants()));
        expense.setExpenseType(expenseRequest.getExpenseType());
        expense.setSplits(expenseRequest.getSplits());

        return expenseRepository.save(expense);
    }

    public List<String> calculateBalances() {
        List<String> balances = new ArrayList<>();

        List<User> users = userRepository.findAll();
        for (User user : users) {
            double balance = 0.0;

            List<Expense> expenses = expenseRepository.findByUsersContaining(user);
            for (Expense expense : expenses) {
                if (expense.getPayer().equals(user.getUserId())) {
                    balance += expense.getAmount();
                } else {
                    double splitAmount = calculateSplitAmount(expense, user);
                    balance -= splitAmount;
                }
            }

            if (balance != 0.0) {
                balances.add(user.getUserId() + " owes " + balance);
            }
        }

        return balances;
    }

    public List<String> calculateUserBalances(String userId) {
        List<String> userBalances = new ArrayList<>();
        User user = (User) userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<Expense> expenses = expenseRepository.findByUsersContaining(user);
            for (Expense expense : expenses) {
                double balance = calculateUserBalance(expense, user);
                if (balance != 0.0) {
                    if (expense.getPayer().equals(user.getUserId())) {
                        userBalances.add(user.getUserId() + " owes " + balance);
                    } else {
                        userBalances.add(user.getUserId() + " is owed " + balance);
                    }
                }
            }
        }

        if (userBalances.isEmpty()) {
            userBalances.add("No balances");
        }

        return userBalances;
    }

    private double calculateSplitAmount(Expense expense, int user) {
        int userIndex = expense.getUsers().indexOf(user);
        if (userIndex >= 0) {
            double split = expense.getSplits().get(userIndex);
            double totalSplit = expense.getSplits().stream().mapToDouble(Double::doubleValue).sum();
            double percent = split / totalSplit;
            return expense.getAmount() * percent;
        }
        return 0.0;
    }

    private double calculateUserBalance(Expense expense, User user) {
        if (expense.getExpenseType() == ExpenseType.EQUAL) {
            return expense.getAmount() / expense.getUsers().length();
        } else if (expense.getExpenseType() == ExpenseType.EXACT) {
            double splitAmount = calculateSplitAmount(expense, user);
            return splitAmount - (expense.getAmount() / expense.getUsers().length());
        }
        return 0.0;
    }

    private List<User> getUsers(List<String> participantIds) {
        List<User> participants = new ArrayList<>();
        for (String participantId : participantIds) {
            User user = (User) userRepository.findById(participantId);
            if (user != null) {
                participants.add(user);
            }
        }
        return participants;
    }}

