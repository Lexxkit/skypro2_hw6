package com.lexxkit.hw6.service.impl;

import com.lexxkit.hw6.data.Employee;
import com.lexxkit.hw6.exception.EmployeeAlreadyAddedException;
import com.lexxkit.hw6.exception.EmployeeNotFoundException;
import com.lexxkit.hw6.exception.EmployeeStorageIsFullException;
import com.lexxkit.hw6.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int MAX_ARRAY_SIZE = 5;

    private List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Natka", "Float"),
            new Employee("Madeleine", "Foad"),
            new Employee("Hurley", "Fraanchyonok"),
            new Employee("Etta", "Stoffer"),
            new Employee("Dame", "Pitkins")
    ));

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_ARRAY_SIZE) {
            throw new EmployeeStorageIsFullException("There is no free space to save new employee.");
        }

        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)){
            throw new EmployeeAlreadyAddedException(employee + " has already been saved.");
        } else {
            employees.add(employee);
        }

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        employees.remove(employee);
        return employee;
    }


    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("There is no such Employee: " + employee);
        }

        return employee;
    }

    @Override
    public List<Employee> getEmployees() {
        return employees;
    }
}
