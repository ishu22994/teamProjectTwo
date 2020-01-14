package com.example.teamProjectTwo.controller;


import com.example.teamProjectTwo.entity.EmployeePostgres;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController
{

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate ;


    private static final String TOPIC ="Kafka1";

    @PostMapping(value = "/publish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String post(@RequestBody EmployeePostgres employee) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send(TOPIC, objectMapper.writeValueAsString(employee));
        return "Published";
    }

}