package ru.skypro.employee.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class Employee {
    private final Integer departmentId;
    private final String firstName;
    private final String lastName;
    private final int salary;

    public Employee(String firstName, String lastName, int salary, Integer departmentId) {
        this.departmentId = departmentId;
        this.salary = salary;
        this.firstName = capitalize(firstName.toLowerCase());
        this.lastName = capitalize(lastName.toLowerCase());
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public int getSalary() {
        return salary;
    }

    @JsonIgnore
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
