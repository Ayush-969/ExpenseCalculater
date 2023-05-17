package com.Expense.ExpenseCalculation.Controller;

import java.util.*;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Expense.ExpenseCalculation.Service.ExpenseService;
import com.Expense.ExpenseCalculation.model.Expense;
import com.Expense.ExpenseCalculation.model.ExpenseRequest;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final ExecutorService userService;

    @Autowired
    public ExpenseController(ExpenseService expenseService, ExecutorService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody ExpenseRequest expenseRequest) {
        Expense createdExpense = expenseService.createExpense(expenseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
    }

    @GetMapping("/balances")
    public ResponseEntity<List<String>> getBalances() {
        List<String> balances = expenseService.calculateBalances();
        return ResponseEntity.ok(balances);
    }

    @GetMapping("/balances/{userId}")
    public ResponseEntity<List<String>> getUserBalances(@PathVariable String userId) {
        List<String> userBalances = expenseService.calculateUserBalances(userId);
        return ResponseEntity.ok(userBalances);
    }

	public ExecutorService getUserService() {
		return userService;
	}
}
