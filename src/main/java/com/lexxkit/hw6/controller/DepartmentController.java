package com.lexxkit.hw6.controller;

import com.lexxkit.hw6.data.Employee;
import com.lexxkit.hw6.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping(path = "/all")
    public Map<String, List<Employee>> getEmployeesForDepartment(@RequestParam(value = "departmentId", required = false) String department) {
        if (department == null) {
            return service.getAllEmployeesGroupByDep();
        }
        return service.getEmployeesForDep(department);
    }

    @GetMapping(path = "/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam("departmentId") String department) {
        return service.getEmployeeWithMaxSalary(department);
    }

    @GetMapping(path = "/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam("departmentId") String department) {
        return service.getEmployeeWithMinSalary(department);
    }
}
