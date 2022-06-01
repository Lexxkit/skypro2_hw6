package com.lexxkit.hw6.service;

import com.lexxkit.hw6.data.Employee;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
}
