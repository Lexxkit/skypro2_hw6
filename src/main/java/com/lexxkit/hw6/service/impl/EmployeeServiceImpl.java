package com.lexxkit.hw6.service.impl;

import com.lexxkit.hw6.data.Employee;
import com.lexxkit.hw6.exception.EmployeeAlreadyAddedException;
import com.lexxkit.hw6.exception.EmployeeNotFoundException;
import com.lexxkit.hw6.exception.EmployeeStorageIsFullException;
import com.lexxkit.hw6.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Employee[] employees = {
            new Employee("Natka", "Float"),
            new Employee("Madeleine", "Foad"),
            new Employee("Hurley", "Fraanchyonok"),
            new Employee("Etta", "Stoffer"),
            new Employee("Dame", "Pitkins"),
    };
    /*
    [{
  "firstName": "Natka",
  "lastName": "Float"
}, {
  "firstName": "Madeleine",
  "lastName": "Foad"
}, {
  "firstName": "Hurley",
  "lastName": "Fraanchyonok"
}, {
  "firstName": "Etta",
  "lastName": "Stoffer"
}, {
  "firstName": "Dame",
  "lastName": "Pitkins"
}]
     */

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        try {
            Employee employee = findEmployee(firstName, lastName);
            throw new EmployeeAlreadyAddedException(employee + " has already been saved.");
        } catch (RuntimeException e) {
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] == null) {
                    Employee employee = new Employee(firstName, lastName);
                    employees[i] = employee;
                    return employee;
                }
            }
            throw new EmployeeStorageIsFullException("There is no free space to save new employee.");
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].equals(employee)) {
                employees[i] = null;
                break;
            }
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].equals(employee)) {
                return employees[i];
            }
        }
        throw new EmployeeNotFoundException("There is no such Employee: " + employee);
    }
}
