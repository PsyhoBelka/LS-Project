package ua.rozhkov.project.validators;

import ua.rozhkov.project.exceptions.BusinessException;

/**
 * Service for validation input data
 */
public interface ValidationService {
    /**
     * Validate age of client
     *
     * @param age inputted age
     * @return true, if validation pass
     * @throws BusinessException with some text explanation if something wrong
     */
    boolean validateAge(int age) throws BusinessException;

    /**
     * Validate email of client
     *
     * @param email inputted email
     * @return true, if validation pass
     * @throws BusinessException with some text explanation if something wrong
     */
    boolean validateEmail(String email) throws BusinessException;

    /**
     * Validate phone number of client
     *
     * @param phoneNum inputted phone number
     * @return true, if validation pass
     * @throws BusinessException with some text explanation if something wrong
     */
    boolean validatePhoneNum(String phoneNum) throws BusinessException;
}
