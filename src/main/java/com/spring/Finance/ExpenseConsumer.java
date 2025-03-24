package com.spring.Finance;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExpenseConsumer {

    @KafkaListener(topics = "expense-topic", groupId = "expense-group")
    public void listenExpenseEvents(String expenseDetails) {
        System.out.println("Received expense event: " + expenseDetails);
    }
}