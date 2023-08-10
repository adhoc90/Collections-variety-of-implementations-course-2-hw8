package ru.skypro.employee.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.employee.exception.InvalidEmployeeDataException;
import ru.skypro.employee.service.EmployeeValidationService;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeValidationServiceImpl implements EmployeeValidationService {
    @Override
    public void validate(String firstName, String lastName) {
        validateName(firstName);
        validateName(lastName);
    }

    private void validateName(String name) {
        if (!isAlpha(name)) {
            throw new InvalidEmployeeDataException("Не корректное значение имени: " + name);
        }
    }
}
