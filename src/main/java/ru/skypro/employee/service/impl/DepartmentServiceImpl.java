package ru.skypro.employee.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.employee.exception.EmployeeNotFoundException;
import ru.skypro.employee.model.Employee;
import ru.skypro.employee.service.DepartmentService;
import ru.skypro.employee.service.EmployeeService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalaryOfDep(Integer departmentId) {
        return employeeService.showEmployeeList()
                .stream()
                .filter(e -> e.getDepartmentId().equals(departmentId))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee getEmployeeWithMinSalaryOfDep(Integer departmentId) {
        return employeeService.showEmployeeList()
                .stream()
                .filter(e -> e.getDepartmentId().equals(departmentId))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesByDepartment(Integer departmentId) {
        return employeeService.showEmployeeList()
                .stream()
                .filter(e -> e.getDepartmentId().equals(departmentId))
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return employeeService.showEmployeeList()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
