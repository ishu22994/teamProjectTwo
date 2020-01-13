package com.example.teamProjectTwo.service.Impl;

import com.example.teamProjectTwo.entity.EmployeeMongoDB;
import com.example.teamProjectTwo.entity.EmployeePostgres;
import com.example.teamProjectTwo.repository.EmployeeMongoRepository;
import com.example.teamProjectTwo.repository.EmployeePostgresRepository;
import com.example.teamProjectTwo.service.WritingService;
import org.springframework.beans.factory.annotation.Autowired;

public class WritingServiceImpl implements WritingService {

    @Autowired
    EmployeeMongoRepository employeeMongoRepository;

    @Autowired
    EmployeePostgresRepository employeePostgresRepository;

    EmployeeMongoDB employeeMongoDB;
    EmployeePostgres employeePostgres;

    @Override
    public void writePostgres() {
        employeePostgresRepository.save(employeePostgres);
    }

    @Override
    public void writeMongo() {
        employeeMongoRepository.save(employeeMongoDB);
    }
}
