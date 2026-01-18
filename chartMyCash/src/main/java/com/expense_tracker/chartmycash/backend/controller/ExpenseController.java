package com.expense_tracker.chartmycash.backend.controller;

import com.expense_tracker.chartmycash.backend.model.Expense;
import com.expense_tracker.chartmycash.backend.repository.ExpenseRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseRepository expenseRepository;

    public ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    // CREATE expense
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        expense.setDate(
                expense.getDate() != null ? expense.getDate() : LocalDate.now()
        );
        return expenseRepository.save(expense);
    }

    // GET all expenses for a user
    @GetMapping("/{userId}")
    public List<Expense> getExpenses(@PathVariable String userId) {
        return expenseRepository.findByUserId(userId);
    }

    // DELETE expense
    @DeleteMapping("/{expenseId}")
    public void deleteExpense(@PathVariable String expenseId) {
        expenseRepository.deleteById(expenseId);
    }
}
