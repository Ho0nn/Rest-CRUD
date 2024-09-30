package com.luve2code.cruddemo.rest;

import com.luve2code.cruddemo.dao.EmployeeDAO;
import com.luve2code.cruddemo.entity.Employee;
import com.luve2code.cruddemo.service.EmployeeService;
import com.luve2code.cruddemo.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {


    private EmployeeService employeeService;

    // quick & dirty implementation : inject EmployeeDAO (constructor injection)
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }
}
