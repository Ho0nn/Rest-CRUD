package com.luve2code.cruddemo.dao;

import com.luve2code.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(int id);
    Employee save(Employee employee);

    void deleteById(int id);
}
