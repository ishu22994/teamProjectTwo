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
    private static int count=0;
    @Autowired
    EmployeeMongoRepository employeeMongoRepository;

    @Autowired
    EmployeePostgresRepository employeePostgresRepository;

    @KafkaListener(topics = "kafka4")
    public void consume(String  employee) throws JsonProcessingException {

            ObjectMapper objectMapper = new ObjectMapper();

            if(count<=150)
            {
                EmployeePostgres employee1 = objectMapper.readValue(employee, EmployeePostgres.class);
                employeePostgresRepository.save(employee1);
                count++;
            }
            else
            {
                EmployeeMongoDB employee1 = objectMapper.readValue(employee, EmployeeMongoDB.class);

                employeeMongoRepository.save(employee1);

            }


    }


}
