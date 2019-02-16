package ua.rozhkov.project.validators.impl;

import ua.rozhkov.project.exceptions.BusinessException;
import ua.rozhkov.project.validators.ValidationService;

public class ValidationServiceImpl implements ValidationService {
    private static ValidationService instance;

    public static ValidationService getInstance() {
        if (instance == null)
            synchronized (ValidationService.class) {
                if (instance == null)
                    instance = new ValidationServiceImpl();
            }
        return instance;
    }

    @Override
    public void validateAge(int age) throws BusinessException {
        if ((age < 0) || (age > 120)) throw new BusinessException("Incorrect age!!!");
    }

    @Override
    public void validateEmail(String email) throws BusinessException {
        throw new BusinessException("Incorrect email!!!");
    }

    @Override
    public void validatePhoneNum(String phoneNum) throws BusinessException {
        throw new BusinessException("Incorrect phone number!!!");
    }
}
