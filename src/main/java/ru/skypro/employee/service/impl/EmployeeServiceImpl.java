package ru.skypro.employee.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.employee.service.EmployeeService;
import ru.skypro.employee.exception.EmployeeAlreadyAddedException;
import ru.skypro.employee.exception.EmployeeNotFoundException;
import ru.skypro.employee.exception.EmployeeStorageIsFullException;
import ru.skypro.employee.model.Employee;
import ru.skypro.employee.service.EmployeeValidationService;

import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();
    private final EmployeeValidationService employeeValidationService;

    public EmployeeServiceImpl(EmployeeValidationService employeeValidationService) {
        this.employeeValidationService = employeeValidationService;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, Integer departmentId) {
        employeeValidationService.validate(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, salary, departmentId);

        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Такого сотрудника в списке нет");
        }
        return employees.remove(key);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;

        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employees.get(key);
    }

    @Override
    public Collection<Employee> showEmployeeList() {
        return employees.values();
    }
}
