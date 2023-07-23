package ru.skypro.employee.employeeService;

import org.springframework.stereotype.Service;
import ru.skypro.employee.exception.EmployeeAlreadyAddedException;
import ru.skypro.employee.exception.EmployeeNotFoundException;
import ru.skypro.employee.exception.EmployeeStorageIsFullException;
import ru.skypro.employee.model.Employee;


import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> employees = new HashMap();
    private final int MAX_COUNT_OF_PEOPLE = 5;

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        if (employees.size() >= MAX_COUNT_OF_PEOPLE) {
            throw new EmployeeStorageIsFullException("Список сотрудников заполнен, нельзя добавить нового");
        }

        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Такого сотрудника в списке нет");
        }
        employees.remove(employee.getFullName());
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    @Override
    public Collection<Employee> showEmployeeList() {
        return employees.values();
    }
}
