package ru.skypro.employee.service;
import ru.skypro.employee.model.Employee;
import java.util.Collection;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, int salary, Integer departmentId);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> showEmployeeList();
}