package com.lexxkit.hw6.service;

import com.lexxkit.hw6.data.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Map<String, List<Employee>> getAllEmployeesGroupByDep();

    Employee getEmployeeWithMaxSalary(String department);

    Employee getEmployeeWithMinSalary(String department);
}
