package com.lexxkit.hw6.service;

import com.lexxkit.hw6.data.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeTestConstants {
    public final static Employee NATKA_FLOAT = new Employee("Natka", "Float", 10, "1");
    public final static Employee MADELEINE_FOAD = new Employee("Madeleine", "Foad", 10, "2");
    public final static Employee HURLEY_FRAAN = new Employee("Hurley", "Fraan", 20, "1");
    public final static Employee DAME_PITKINS = new Employee("Dame", "Pitkins", 15, "1");
    public final static Employee IVAN_IVANOV = new Employee("Ivan", "Ivanov", 30, "2");
    public final static Employee BORIS_BORISOV = new Employee("Boris", "Borisov", 15, "3");
    public final static Employee WRONG_NAME_SPELLING = new Employee("Test_1", "Test!", 10, "1");
    public final static Map<String, Employee> ALL_EMPLOYEES_MAP = new HashMap<>(
            Map.of(
                    "Natka Float", NATKA_FLOAT,
                    "Madeleine Foad", MADELEINE_FOAD,
                    "Hurley Fraanchyonok", HURLEY_FRAAN,
                    "Dame Pitkins", DAME_PITKINS,
                    "Ivan Ivanov", IVAN_IVANOV
            )
    );
    public final static Map<String, List<Employee>> ALL_EMPLOYEES_GROUP_BY_DEPARTMENT =  ALL_EMPLOYEES_MAP.values().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));
}
