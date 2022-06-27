package com.lexxkit.hw6.service;

import com.lexxkit.hw6.data.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Map<String, List<Employee>> getAllEmployeesGroupByDep();

    Map<String, List<Employee>> getEmployeesForDep(String department);

    Employee getEmployeeWithMaxSalary(String department);

    Employee getEmployeeWithMinSalary(String department);
}
