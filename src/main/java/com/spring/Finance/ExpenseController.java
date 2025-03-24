package com.spring.Finance;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        Expense savedExpense = expenseRepository.save(expense);

        // Publish expense event to Kafka
        kafkaTemplate.send("expense-topic", savedExpense.toString());

        return savedExpense;
    }

    @GetMapping
    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }
}