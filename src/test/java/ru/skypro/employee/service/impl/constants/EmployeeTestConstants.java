package ru.skypro.employee.service.impl.constants;

import ru.skypro.employee.model.Employee;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeTestConstants {

    public static final String FIRST_NAME = "Ivan";
    public static final String LAST_NAME = "Ivanov";
    public static final String FIRST_NAME2 = "Petr";
    public static final String LAST_NAME2 = "Petrov";
    public static final String FIRST_NAME3 = "Kiril";
    public static final String LAST_NAME3 = "Kirillov";

    public static final Integer SALARY = 100;
    public static final Integer MAX_SALARY = 100_000;

    public static final Integer DEPARTMENT_ID = 1;
    public static final Integer DEPARTMENT_ID2 = 2;

    public static final List<Employee> TEST_LIST_OF_EMPLOYEE = new ArrayList<>();

    public static final Employee MAX_SALARY_EMPLOYEE = new Employee(FIRST_NAME, LAST_NAME, MAX_SALARY, DEPARTMENT_ID);
    public static final Employee MIN_SALARY_EMPLOYEE = new Employee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
    public static final Employee OTHER_DEPARTMENT_EMPLOYEE = new Employee(FIRST_NAME3, LAST_NAME3, SALARY, DEPARTMENT_ID2);

    public static final List<Employee> EMPLOYEES = List.of(MAX_SALARY_EMPLOYEE, MIN_SALARY_EMPLOYEE);
    public static final List<Employee> DIFFERENT_DEPARTMENT_EMPLOYEES = List.of(MAX_SALARY_EMPLOYEE,
            MIN_SALARY_EMPLOYEE, OTHER_DEPARTMENT_EMPLOYEE);

    public static final Map<Integer, List<Employee>> EMPLOYEES_MAP = Stream.of(MAX_SALARY_EMPLOYEE, MIN_SALARY_EMPLOYEE)
            .collect(Collectors.toMap(Employee::getDepartmentId,
                    Collections::singletonList,
                    (list1, list2) -> {
                        List<Employee> mergedList = new ArrayList<>(list1);
                        mergedList.addAll(list2);
                        return mergedList;
                    }));
    public static final int EMPLOYEES_TOTAL_SALARY = EMPLOYEES.stream().mapToInt(Employee::getSalary).sum();

    public static final Map<Integer, List<Employee>> EMPLOYEES_BY_DEPARTMENT_MAP = DIFFERENT_DEPARTMENT_EMPLOYEES
            .stream().collect(Collectors.groupingBy(Employee::getDepartmentId));

    public static final Map<Integer, List<Employee>> EMPLOYEES_BY_CURRENT_DEPARTMENT_MAP = DIFFERENT_DEPARTMENT_EMPLOYEES
            .stream()
            .filter(e -> e.getDepartmentId().equals(DEPARTMENT_ID2))
            .collect(Collectors.groupingBy(Employee::getDepartmentId));
}