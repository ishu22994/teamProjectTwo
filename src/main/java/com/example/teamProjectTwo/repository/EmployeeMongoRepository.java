package com.example.teamProjectTwo.repository;

import com.example.teamProjectTwo.entity.EmployeeMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  EmployeeMongoRepository extends MongoRepository<EmployeeMongoDB,String> {

}
