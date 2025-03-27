package util;

import exception.*;

import java.time.LocalDate;
import java.util.UUID;

public class ValidationUtil {

    public static void validateAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException(ErrorCode.INVALID_AMOUNT.getMessage());
        }
    }

    public static void validateAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new EmptyInputException(ErrorCode.EMPTY_INPUT.getMessage());
        }
        if (!accountNumber.matches("^[0-9]{10}$")) {
            throw new InvalidAccountNumberException(ErrorCode.INVALID_ACCOUNT_NUMBER.getMessage());
        }
    }

    public static void validateCustomerId(UUID customerId) {
        if (customerId == null) {
            throw new InvalidCustomerIdException(ErrorCode.INVALID_CUSTOMER_ID.getMessage());
        }
    }

    public static void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new EmptyInputException(ErrorCode.EMPTY_INPUT.getMessage());
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidEmailException(ErrorCode.INVALID_EMAIL.getMessage());
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new EmptyInputException(ErrorCode.EMPTY_INPUT.getMessage());
        }
        if (!phoneNumber.matches("^[0-9]{10}$")) {
            throw new InvalidPhoneException(ErrorCode.INVALID_PHONE.getMessage());
        }
    }

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new EmptyInputException(ErrorCode.EMPTY_INPUT.getMessage());
        }
        if (name.length() < 2) {
            throw new InvalidNameException(ErrorCode.INVALID_NAME.getMessage());
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new EmptyInputException(ErrorCode.EMPTY_INPUT.getMessage());
        }
        if (password.length() < 6) {
            throw new InvalidPasswordException(ErrorCode.INVALID_PASSWORD.getMessage());
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new InvalidPasswordException(ErrorCode.INVALID_PASSWORD.getMessage());
        }
        if (!password.matches(".*[a-z].*")) {
            throw new InvalidPasswordException(ErrorCode.INVALID_PASSWORD.getMessage());
        }
        if (!password.matches(".*\\d.*")) {
            throw new InvalidPasswordException(ErrorCode.INVALID_PASSWORD.getMessage());
        }
    }

    public static void validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new EmptyInputException(ErrorCode.EMPTY_INPUT.getMessage());
        }
        if (address.length() < 10) {
            throw new InvalidAddressException(ErrorCode.INVALID_ADDRESS.getMessage());
        }
    }

    public static void validateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new EmptyInputException(ErrorCode.EMPTY_INPUT.getMessage());
        }
        if (dateOfBirth.isAfter(LocalDate.now())) {
            throw new InvalidDateException(ErrorCode.INVALID_DATE.getMessage());
        }
        if (dateOfBirth.isBefore(LocalDate.now().minusYears(100))) {
            throw new InvalidDateException(ErrorCode.INVALID_DATE.getMessage());
        }
    }
}