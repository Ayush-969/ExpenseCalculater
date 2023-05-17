package com.Expense.ExpenseCalculation.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.Expense.ExpenseCalculation.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	List<User> findAll();

	Object findById(String userId);
}

