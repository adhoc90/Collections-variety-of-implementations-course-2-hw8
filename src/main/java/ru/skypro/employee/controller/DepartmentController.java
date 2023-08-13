package ru.skypro.employee.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.employee.model.Employee;
import ru.skypro.employee.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{id}/salary/max")
    public Employee getEmployeeWithMaxSalary(@PathVariable("id") Integer departmentId) {
        return departmentService.getEmployeeWithMaxSalaryOfDep(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public Employee getEmployeeWithMinSalary(@PathVariable("id") Integer departmentId) {
        return departmentService.getEmployeeWithMinSalaryOfDep(departmentId);
    }

    @GetMapping("/{id}/employees")
    public Map<Integer, List<Employee>> getEmployeesByDepartment(@PathVariable("id") Integer departmentId) {
        return departmentService.getEmployeesByDepartment(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllEmployeesByDepartment() {
        return departmentService.getAllEmployeesByDepartment();
    }

    @GetMapping("/{id}/salary/sum")
    public Integer getSumSalaryByDepartment(@PathVariable("id") Integer departmentId) {
        return departmentService.getSumSalaryOfDep(departmentId);
    }
}


