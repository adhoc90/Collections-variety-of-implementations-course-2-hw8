package ru.skypro.employee.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.employee.exception.EmployeeNotFoundException;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static ru.skypro.employee.service.impl.constants.EmployeeTestConstants.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void ShouldReturnDepSalarySum() {
        when(employeeService.showEmployeeList()).thenReturn(EMPLOYEES);
        assertEquals(EMPLOYEES_TOTAL_SALARY, departmentService.getSumSalaryOfDep(DEPARTMENT_ID));
    }

    @Test
    public void ShouldReturnMaxSalaryEmployee() {
        when(employeeService.showEmployeeList()).thenReturn(EMPLOYEES);
        assertEquals(MAX_SALARY_EMPLOYEE, departmentService.getEmployeeWithMaxSalaryOfDep(DEPARTMENT_ID));
    }

    @Test
    public void ShouldReturnMinSalaryEmployee() {
        when(employeeService.showEmployeeList()).thenReturn(EMPLOYEES);
        assertEquals(MIN_SALARY_EMPLOYEE, departmentService.getEmployeeWithMinSalaryOfDep(DEPARTMENT_ID));
    }

    @Test
    public void ShouldThrowEmployeeNotFoundExceptionWhenFineEmployeeWithMaxSalary() {
        when(employeeService.showEmployeeList()).thenReturn(emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmployeeWithMaxSalaryOfDep(DEPARTMENT_ID));
    }

    @Test
    public void ShouldThrowEmployeeNotFoundExceptionWhenFineEmployeeWithMinSalary() {
        when(employeeService.showEmployeeList()).thenReturn(emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmployeeWithMinSalaryOfDep(DEPARTMENT_ID));
    }

    @Test
    public void ShouldReturnEmployeesByDepId() {
        when(employeeService.showEmployeeList()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);
        assertEquals(EMPLOYEES_MAP, departmentService.getEmployeesByDepartment(DEPARTMENT_ID));
        assertEquals(EMPLOYEES_BY_CURRENT_DEPARTMENT_MAP, departmentService.getEmployeesByDepartment(DEPARTMENT_ID2));
    }

    @Test
    public void ShouldReturnAllEmployees() {
        when(employeeService.showEmployeeList()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);
        assertEquals(EMPLOYEES_BY_DEPARTMENT_MAP, departmentService.getAllEmployeesByDepartment());
    }
}
