package com.lexxkit.hw6.service.impl;

import com.lexxkit.hw6.data.Employee;
import com.lexxkit.hw6.exception.EmployeeNotFoundException;
import com.lexxkit.hw6.service.DepartmentService;
import com.lexxkit.hw6.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Map<String, List<Employee>> getAllEmployeesGroupByDep() {
        Map<String, Employee> allEmployees = employeeService.getEmployees();

        return allEmployees.values().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));
    }

    @Override
    public Map<String, List<Employee>> getEmployeesForDep(String department) {

        return getEmployeeStreamForDepartment(department)
                .collect(Collectors.groupingBy(e -> e.getDepartment()));
    }

    @Override
    public Employee getEmployeeWithMaxSalary(String department) {

        return getEmployeeStreamForDepartment(department)
                .max(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public Employee getEmployeeWithMinSalary(String department) {

        return getEmployeeStreamForDepartment(department)
                .min(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    private Stream<Employee> getEmployeeStreamForDepartment(String department) {
        Map<String, Employee> allEmployees = employeeService.getEmployees();

        return allEmployees.values().stream()
                .filter(e -> e.getDepartment().equals(department));
    }
}
