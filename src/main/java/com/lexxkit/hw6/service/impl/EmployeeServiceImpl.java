package com.lexxkit.hw6.service.impl;

import com.lexxkit.hw6.data.Employee;
import com.lexxkit.hw6.exception.EmployeeAlreadyAddedException;
import com.lexxkit.hw6.exception.EmployeeNotFoundException;
import com.lexxkit.hw6.exception.EmployeeStorageIsFullException;
import com.lexxkit.hw6.exception.WrongNameSpellingException;
import com.lexxkit.hw6.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int MAX_ARRAY_SIZE = 5;

    private Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>(
                Map.of(
                        "Natka Float", new Employee("Natka", "Float", 10, "1"),
                        "Madeleine Foad", new Employee("Madeleine", "Foad", 10, "2"),
                        "Hurley Fraanchyonok", new Employee("Hurley", "Fraanchyonok", 20, "1"),
                        "Dame Pitkins", new Employee("Dame", "Pitkins", 15, "1")
                )
        );
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, double salary, String department) {
        validateNameSpelling(firstName, lastName);
        if (employees.size() >= MAX_ARRAY_SIZE) {
            throw new EmployeeStorageIsFullException("There is no free space to save new employee.");
        }

        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())){
            throw new EmployeeAlreadyAddedException();
        }

        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        validateNameSpelling(firstName, lastName);

        String employeeKey = getKeyByFirstNameAndLastName(firstName, lastName);
        return employees.remove(employeeKey);
    }


    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateNameSpelling(firstName, lastName);

        String employeeKey = getKeyByFirstNameAndLastName(firstName, lastName);
        return employees.get(employeeKey);
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return Collections.unmodifiableMap(employees);
    }

    private String getKeyByFirstNameAndLastName(String firstName, String lastName) {
        return employees.keySet().stream()
                .filter(e -> e.equals(firstName + " " + lastName))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    private void validateNameSpelling(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new WrongNameSpellingException();
        }
    }
}
