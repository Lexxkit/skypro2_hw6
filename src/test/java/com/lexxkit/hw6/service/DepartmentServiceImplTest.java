package com.lexxkit.hw6.service;

import com.lexxkit.hw6.data.Employee;
import com.lexxkit.hw6.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static com.lexxkit.hw6.service.EmployeeTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    @Test
    void shouldPrint() {
        when(employeeServiceMock.getEmployees()).thenReturn(ALL_EMPLOYEES_MAP);
        Map<String, List<Employee>> result = out.getAllEmployeesGroupByDep();

        assertIterableEquals(ALL_EMPLOYEES_GROUP_BY_DEPARTMENT.values(), result.values());
    }
}