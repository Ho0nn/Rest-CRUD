package com.luve2code.cruddemo.rest;

import com.luve2code.cruddemo.dao.EmployeeDAO;
import com.luve2code.cruddemo.entity.Employee;
import com.luve2code.cruddemo.service.EmployeeService;
import com.luve2code.cruddemo.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {


    private EmployeeService employeeService;

    // quick & dirty implementation : inject EmployeeDAO (constructor injection)
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
    @PostMapping("")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    @PutMapping("")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee emp = employeeService.save(employee);
        return emp;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id){
     Employee emp = employeeService.findById(id);
     if (emp==null)
         throw new RuntimeException("Employee Id is not Found !");
         employeeService.deleteById(id);
     return "Deleted Successfully";

    }
}
