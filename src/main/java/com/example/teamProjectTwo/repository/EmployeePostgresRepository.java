package com.example.teamProjectTwo.repository;

import com.example.teamProjectTwo.entity.EmployeePostgres;
import org.springframework.data.repository.CrudRepository;

public interface EmployeePostgresRepository extends CrudRepository<EmployeePostgres,String> {
}
