package com.luve2code.cruddemo.dao;

import com.luve2code.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOIpml implements EmployeeDAO{
    private EntityManager  entityManager;

    @Autowired
    public EmployeeDAOIpml(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // Create query
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);

        // execute query
        List<Employee> employees = query.getResultList();

        // return results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        // get employee
        Employee employee = entityManager.find(Employee.class, theId);

        // return employee
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee dbEmployee=entityManager.merge(employee);

        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {

        // find by ID
        Employee employee=entityManager.find(Employee.class, id);

        // delete employee
        entityManager.remove(employee);

    }
}
