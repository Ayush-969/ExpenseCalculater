package com.Expense.ExpenseCalculation.Repository;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.Expense.ExpenseCalculation.model.Expense;
import com.Expense.ExpenseCalculation.model.User;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUsersContaining(User user);

	Expense save(Expense expense);
}
