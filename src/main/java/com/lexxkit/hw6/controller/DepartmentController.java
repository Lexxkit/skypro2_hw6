package com.lexxkit.hw6.controller;

import com.lexxkit.hw6.data.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    @GetMapping(path = "/all")
    public String getEmployeesForDepartment(@RequestParam(value = "departmentId", required = false) String department) {
        if (department == null) {
            return "All employees grouped by departments.";
        }
        return "All employees for " + department;
    }

    @GetMapping(path = "/max-salary")
    public String getEmployeeWithMaxSalary(@RequestParam("departmentId") String department) {
        return "Max salary for " + department;
    }

    @GetMapping(path = "/min-salary")
    public String getEmployeeWithMinSalary(@RequestParam("departmentId") String department) {
        return "Min salary for " + department;
    }
}
