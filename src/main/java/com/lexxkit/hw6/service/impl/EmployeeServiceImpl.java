package com.lexxkit.hw6.service.impl;

import com.lexxkit.hw6.data.Employee;
import com.lexxkit.hw6.exception.EmployeeAlreadyAddedException;
import com.lexxkit.hw6.exception.EmployeeNotFoundException;
import com.lexxkit.hw6.exception.EmployeeStorageIsFullException;
import com.lexxkit.hw6.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int MAX_ARRAY_SIZE = 5;

    private Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>(
                Map.of(
                        "Natka Float", new Employee("Natka", "Float"),
                        "Madeleine Foad", new Employee("Madeleine", "Foad")
                )
        );
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_ARRAY_SIZE) {
            throw new EmployeeStorageIsFullException("There is no free space to save new employee.");
        }

        String employeeName = firstName + " " + lastName;
        if (employees.containsKey(employeeName)){
            throw new EmployeeAlreadyAddedException(employeeName + " has already been saved.");
        }

        Employee employee = new Employee(firstName, lastName);
        employees.put(employeeName, employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String employeeName = firstName + " " + lastName;
        Employee employee = employees.remove(employeeName);
        if (employee != null) {
            return employee;
        }
        throw new EmployeeNotFoundException("There is no such Employee: " + employeeName);
    }


    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String employeeName = firstName + " " + lastName;
        Employee employee = employees.get(employeeName);
        if (employee != null) {
            return employee;
        }
        throw new EmployeeNotFoundException("There is no such Employee: " + employeeName);
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return Collections.unmodifiableMap(employees);
    }
}
