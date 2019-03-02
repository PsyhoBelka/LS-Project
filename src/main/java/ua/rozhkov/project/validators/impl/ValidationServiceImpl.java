package ua.rozhkov.project.validators.impl;

import ua.rozhkov.project.validators.ValidationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public boolean validateAge(int age) {
        return ((age >= 0) && (age <= 120));
    }

    @Override
    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w.]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean validatePhoneNum(String phoneNum) {
        Pattern pattern = Pattern.compile("(050|067|097)([\\d]{7})");
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }
}
