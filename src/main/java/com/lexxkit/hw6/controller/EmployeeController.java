package com.lexxkit.hw6.controller;

import com.lexxkit.hw6.data.Employee;
import com.lexxkit.hw6.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    Map<String, Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "/add")
    Employee addEmployee(@RequestParam("firstname")String firstName,
                         @RequestParam("lastname") String lastName,
                         @RequestParam("salary") double salary,
                         @RequestParam("department") String department
                         ) {

        return employeeService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/remove")
    Employee removeEmployee(@RequestParam("firstname")String firstName,
                            @RequestParam("lastname") String lastName) {

        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(path = "/find")
    Employee findEmployee(@RequestParam("firstname")String firstName,
                          @RequestParam("lastname") String lastName) {

        return employeeService.findEmployee(firstName, lastName);
    }
}

