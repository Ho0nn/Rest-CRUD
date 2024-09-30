package com.luve2code.cruddemo.dao;
import com.luve2code.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


}
