package ua.rozhkov.project.validators;

import ua.rozhkov.project.exceptions.BusinessException;

public interface ValidationService {
    boolean validateAge(int age) throws BusinessException;

    boolean validateEmail(String email) throws BusinessException;

    boolean validatePhoneNum(String phoneNum) throws BusinessException;
}
