package ru.skypro.employee.service;
import ru.skypro.employee.model.Employee;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalaryOfDep(Integer departmentId);

    Employee getEmployeeWithMinSalaryOfDep(Integer departmentId);

    Map<Integer, List<Employee>> getEmployeesByDepartment(Integer departmentId);

    Map<Integer, List<Employee>> getAllEmployeesByDepartment();

    Integer getSumSalaryOfDep(Integer departmentId);
}
