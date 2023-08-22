package ru.skypro.employee.service.impl;

import org.junit.jupiter.api.Test;
import ru.skypro.employee.exception.EmployeeAlreadyAddedException;
import ru.skypro.employee.exception.EmployeeNotFoundException;
import ru.skypro.employee.model.Employee;
import ru.skypro.employee.service.EmployeeValidationService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.skypro.employee.service.impl.constants.EmployeeTestConstants.*;

class EmployeeServiceImplTest {

    private final EmployeeValidationService employeeValidationService = new EmployeeValidationServiceImpl();
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeValidationService);

    @Test
    public void ShouldAddEmployee() {
        Employee employee = new Employee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertFalse(employeeService.showEmployeeList().contains(employee));

        Employee addedEmployee = employeeService.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(addedEmployee, employee);
        assertTrue(employeeService.showEmployeeList().contains(employee));
        assertEquals(1, employeeService.showEmployeeList().size());
    }

    @Test
    public void shouldThrowEmployeeAlreadyAddedException() {
        Employee addedEmployee = employeeService.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertTrue(employeeService.showEmployeeList().contains(addedEmployee));
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService
                .addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID));
    }

    @Test
    public void ShouldDeleteEmployee() {
        Employee addedEmployee = employeeService.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertTrue(employeeService.showEmployeeList().contains((addedEmployee)));
        employeeService.deleteEmployee(FIRST_NAME, LAST_NAME);
        assertFalse(employeeService.showEmployeeList().contains((addedEmployee)));
        assertIterableEquals(TEST_LIST_OF_EMPLOYEE, employeeService.showEmployeeList());
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenDeleteEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService
                .deleteEmployee(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void ShouldFineEmployee() {
        Employee addedEmployee = employeeService.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(addedEmployee, employeeService.findEmployee(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFineEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService
                .findEmployee(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void ShouldFindAllEmployees() {
        Employee addedEmployee1 = employeeService.addEmployee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        Employee addedEmployee2 = employeeService.addEmployee(FIRST_NAME2, LAST_NAME2, SALARY, DEPARTMENT_ID);

        assertIterableEquals(List.of(addedEmployee1, addedEmployee2), employeeService.showEmployeeList());
    }
}
