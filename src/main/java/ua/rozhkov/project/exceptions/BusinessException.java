package ua.rozhkov.project.exceptions;

public class BusinessException extends Exception {
    public BusinessException(String msg) {
        System.out.println(msg);
    }
}
