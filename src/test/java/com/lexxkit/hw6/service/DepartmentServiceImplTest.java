package com.lexxkit.hw6.service;

import com.lexxkit.hw6.data.Employee;
import com.lexxkit.hw6.exception.EmployeeNotFoundException;
import com.lexxkit.hw6.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static com.lexxkit.hw6.service.EmployeeTestConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    void setUp() {
        when(employeeServiceMock.getEmployees()).thenReturn(ALL_EMPLOYEES_MAP);
    }

    @Test
    void shouldReturnMapOfAllEmployeesGroupedByDep() {
        Map<String, List<Employee>> result = out.getAllEmployeesGroupByDep();

        assertThat(result).containsExactlyInAnyOrderEntriesOf(ALL_EMPLOYEES_GROUP_BY_DEPARTMENT);
    }

    @Test
    void shouldReturnEmployeesForSpecifiedDepartment() {
        Map<String, List<Employee>> result = out.getEmployeesForDep(DEP_1);

        assertThat(result).containsExactlyInAnyOrderEntriesOf(EMPLOYEES_FOR_DEP_1);
    }

    @Test
    void shouldReturnEmptyMapWhenDepartmentNonExistent() {
        Map<String, List<Employee>> result = out.getEmployeesForDep(NON_EXISTENT_DEP);

        assertThat(result).hasSize(0);
    }

    @Test
    void shouldReturnEmployeeWithMinSalaryForSpecifiedDepartment() {
        Employee result = out.getEmployeeWithMinSalary(DEP_1);

        assertEquals(EMPLOYEE_MIN_SALARY_DEP_1, result);
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenMinSalaryAndDepartmentNonExistent() {

        assertThrows(EmployeeNotFoundException.class, () -> out.getEmployeeWithMinSalary(NON_EXISTENT_DEP));
    }

    @Test
    void shouldReturnEmployeeWithMaxSalaryForSpecifiedDepartment() {
        Employee result = out.getEmployeeWithMaxSalary(DEP_1);

        assertEquals(EMPLOYEE_MAX_SALARY_DEP_1, result);
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenMaxSalaryAndDepartmentNonExistent() {

        assertThrows(EmployeeNotFoundException.class, () -> out.getEmployeeWithMaxSalary(NON_EXISTENT_DEP));
    }
}