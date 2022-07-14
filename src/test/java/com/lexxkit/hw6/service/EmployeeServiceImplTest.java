package com.lexxkit.hw6.service;

import com.lexxkit.hw6.data.Employee;
import com.lexxkit.hw6.exception.EmployeeAlreadyAddedException;
import com.lexxkit.hw6.exception.EmployeeNotFoundException;
import com.lexxkit.hw6.exception.EmployeeStorageIsFullException;
import com.lexxkit.hw6.exception.WrongNameSpellingException;
import com.lexxkit.hw6.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;

import static com.lexxkit.hw6.service.EmployeeTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceImplTest {

    private final EmployeeService out = new EmployeeServiceImpl();

    @Test
    void shouldAddToMapAndReturnEmployeeWhenMapHasSpace() {
        Employee result = out.addEmployee(NATKA_FLOAT.getFirstName(), NATKA_FLOAT.getLastName(), NATKA_FLOAT.getSalary(), NATKA_FLOAT.getDepartment());
        int expectedMapSize = 1;

        assertEquals(NATKA_FLOAT, result);
        assertEquals(expectedMapSize, out.getEmployees().size());
    }

    @Test
    void shouldThrowEmployeeAlreadyAddedExceptionWhenAddSameEmployee() {
        Employee employee = out.addEmployee(NATKA_FLOAT.getFirstName(), NATKA_FLOAT.getLastName(), NATKA_FLOAT.getSalary(), NATKA_FLOAT.getDepartment());

        assertThrows(EmployeeAlreadyAddedException.class, () -> out.addEmployee(
                        NATKA_FLOAT.getFirstName(), NATKA_FLOAT.getLastName(),
                        NATKA_FLOAT.getSalary(), NATKA_FLOAT.getDepartment()
                )
        );
    }

    @Test
    void shouldThrowEmployeeStorageIsFullExceptionWhenMapContainsFiveEmployees() {
        Employee employee1 = out.addEmployee(NATKA_FLOAT.getFirstName(), NATKA_FLOAT.getLastName(), NATKA_FLOAT.getSalary(), NATKA_FLOAT.getDepartment());
        Employee employee2 = out.addEmployee(MADELEINE_FOAD.getFirstName(), MADELEINE_FOAD.getLastName(), MADELEINE_FOAD.getSalary(), MADELEINE_FOAD.getDepartment());
        Employee employee3 = out.addEmployee(HURLEY_FRAAN.getFirstName(), HURLEY_FRAAN.getLastName(), HURLEY_FRAAN.getSalary(), HURLEY_FRAAN.getDepartment());
        Employee employee4 = out.addEmployee(DAME_PITKINS.getFirstName(), DAME_PITKINS.getLastName(), DAME_PITKINS.getSalary(), DAME_PITKINS.getDepartment());
        Employee employee5 = out.addEmployee(IVAN_IVANOV.getFirstName(), IVAN_IVANOV.getLastName(), IVAN_IVANOV.getSalary(), IVAN_IVANOV.getDepartment());

        assertThrows(EmployeeStorageIsFullException.class, () -> out.addEmployee(
                        BORIS_BORISOV.getFirstName(), BORIS_BORISOV.getLastName(),
                BORIS_BORISOV.getSalary(), BORIS_BORISOV.getDepartment()
                )
        );
    }

    @Test
    void shouldThrowWrongNameSpellingExceptionWhenAddEmployeeWithBadCharacters() {
        assertThrows(WrongNameSpellingException.class,
                () -> out.addEmployee(WRONG_NAME_SPELLING.getFirstName(), WRONG_NAME_SPELLING.getLastName(),
                        WRONG_NAME_SPELLING.getSalary(), WRONG_NAME_SPELLING.getDepartment()));
    }

    @Test
    void shouldReturnMapOfSizeZeroWhenMapIsEmpty() {
        int result = out.getEmployees().size();

        int expectedMapSize = 0;
        assertEquals(expectedMapSize, result);
    }

    @Test
    void shouldReturnMapOfSizeOneWhenMapHasOneEmployee() {
        out.addEmployee(NATKA_FLOAT.getFirstName(), NATKA_FLOAT.getLastName(), NATKA_FLOAT.getSalary(), NATKA_FLOAT.getDepartment());

        int result = out.getEmployees().size();

        int expectedMapSize = 1;
        assertEquals(expectedMapSize, result);
    }

    @Test
    void shouldRemoveFromMapAndReturnEmployeeWhenEmployeeIsInMap() {
        Employee employee1 = out.addEmployee(NATKA_FLOAT.getFirstName(), NATKA_FLOAT.getLastName(), NATKA_FLOAT.getSalary(), NATKA_FLOAT.getDepartment());

        Employee result = out.removeEmployee(NATKA_FLOAT.getFirstName(), NATKA_FLOAT.getLastName());

        int expectedMapSize = 0;
        assertEquals(NATKA_FLOAT, result);
        assertEquals(expectedMapSize, out.getEmployees().size());
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenRemoveNonExistentEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> out.removeEmployee(NATKA_FLOAT.getFirstName(), NATKA_FLOAT.getLastName()));
    }

    @Test
    void shouldThrowWrongNameSpellingExceptionWhenRemoveEmployeeWithBadCharacters() {
        assertThrows(WrongNameSpellingException.class,
                () -> out.removeEmployee(WRONG_NAME_SPELLING.getFirstName(), WRONG_NAME_SPELLING.getLastName()));
    }

    @Test
    void shouldReturnEmployeeWhenMapHasSameEmployee() {
        Employee employee1 = out.addEmployee(NATKA_FLOAT.getFirstName(), NATKA_FLOAT.getLastName(), NATKA_FLOAT.getSalary(), NATKA_FLOAT.getDepartment());

        Employee result = out.findEmployee(NATKA_FLOAT.getFirstName(), NATKA_FLOAT.getLastName());
        assertEquals(NATKA_FLOAT, result);
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionWhenFindNonExistentEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee(NATKA_FLOAT.getFirstName(), NATKA_FLOAT.getLastName()));
    }

    @Test
    void shouldThrowWrongNameSpellingExceptionWhenFindEmployeeWithBadCharacters() {
        assertThrows(WrongNameSpellingException.class,
                () -> out.findEmployee(WRONG_NAME_SPELLING.getFirstName(), WRONG_NAME_SPELLING.getLastName()));
    }
}