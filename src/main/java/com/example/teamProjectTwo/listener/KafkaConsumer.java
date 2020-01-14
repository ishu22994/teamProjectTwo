package com.example.teamProjectTwo.listener;


import com.example.teamProjectTwo.entity.EmployeePostgres;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer
{
    @KafkaListener(topics = "kafka1")
    public void consume(String  employee) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeePostgres employee1 = objectMapper.readValue(employee, EmployeePostgres.class);
        System.out.println("Consumed message: "+employee1.getFirstName());
    }
}
