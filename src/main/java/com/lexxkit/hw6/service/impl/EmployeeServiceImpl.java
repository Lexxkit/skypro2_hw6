package com.lexxkit.hw6.service.impl;

import com.lexxkit.hw6.data.Employee;
import com.lexxkit.hw6.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Employee[] employees;
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
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = new Employee(firstName, lastName);
            }
        }
        return null;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        return null;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        return null;
    }
}
