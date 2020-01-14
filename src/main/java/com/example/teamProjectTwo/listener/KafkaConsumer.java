package com.example.teamProjectTwo.listener;


import com.example.teamProjectTwo.entity.EmployeeMongoDB;
import com.example.teamProjectTwo.entity.EmployeePostgres;
import com.example.teamProjectTwo.repository.EmployeeMongoRepository;
import com.example.teamProjectTwo.repository.EmployeePostgresRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer
{
    @Autowired
    EmployeeMongoRepository employeeMongoRepository;

    @Autowired
    EmployeePostgresRepository employeePostgresRepository;

    @KafkaListener(topics = "kafka1")
    public void consumePostgres(String  employee) throws JsonProcessingException {
        for(int i=0;i<150;i++) {
            ObjectMapper objectMapper = new ObjectMapper();
            EmployeePostgres employee1 = objectMapper.readValue(employee, EmployeePostgres.class);
            System.out.println("Consumed message: " + employee1.getFirstName());
            employeePostgresRepository.save(employee1);
        }

    }

    @KafkaListener(topics = "kafka1")
    public void consumeMongo(String  employee) throws JsonProcessingException {
        for(int i=0;i<150;i++) {
            ObjectMapper objectMapper = new ObjectMapper();
            EmployeeMongoDB employee1 = objectMapper.readValue(employee, EmployeeMongoDB.class);
            System.out.println("Consumed message: " + employee1.getFirstName());
            employeeMongoRepository.save(employee1);
        }

    }
}
