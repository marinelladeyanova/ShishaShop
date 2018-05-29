package com.example.model.pojos;

import com.example.model.exeptions.ValidationExeption;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final int STRRING_MIN_LENGTH = 3;
    private static final int INT_MIN_VALUE = 0;

    public static final String PHONE_NUMBER_PREFIX = "08";
    public static final String EMAIL_REGEX = "^(.+)@([^@]+[^.])$";

    public static final String REGEX_CONTAINS_DIGIT = ".*\\d+.*";
    public static final String REGEX_CONTAINS_LOWERCASE_LETTER = ".*[a-z]+.*";
    public static final String REGEX_CONTAINS_UPPERCASE_LETTER = ".*[A-Z]+.*";

    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 20;

    public static final String MESSAGE_VALIDATE_STRING = String.format("Must contains %s symbols.", STRRING_MIN_LENGTH);
    public static final String MESSAGE_VALIDATE_INT = "Must be positive value.";
    public static final String MESSAGE_PHONE_NUMBER = String.format("Must starts with \"%s\", must contains digits only and has 10 characters length.", PHONE_NUMBER_PREFIX);
    public static final String MESSAGE_EMAIL = "Incorrect email";
    public static final String MESSAGE_NO_VALUE = "No value";


    static Validator instance;

    public static Validator getInstance() {
        if (instance == null) {
            instance = new Validator();
        }
        return instance;
    }

    public String validateString(String string) throws ValidationExeption {
        if (string == null || string.length() < STRRING_MIN_LENGTH)
            throw new ValidationExeption(MESSAGE_VALIDATE_STRING);
        return string;
    }

    public int validateInt(int  number) throws ValidationExeption {
        if (number <= INT_MIN_VALUE){
            throw new ValidationExeption(MESSAGE_VALIDATE_INT);
        }
        return number;
    }

    public String validatePassword(String password) throws ValidationExeption {
        if (password == null)
            throw new ValidationExeption("Write your password.");
        if (password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH)
            throw new ValidationExeption("Password must be between " + PASSWORD_MIN_LENGTH + " and " + PASSWORD_MAX_LENGTH + " symbols.");
        if (!Pattern.compile(REGEX_CONTAINS_DIGIT).matcher(password).matches())
            throw new ValidationExeption("Must contain digit.");
        if (!Pattern.compile(REGEX_CONTAINS_LOWERCASE_LETTER).matcher(password).matches())
            throw new ValidationExeption("Must contain lowercase letter.");
        if (!Pattern.compile(REGEX_CONTAINS_UPPERCASE_LETTER).matcher(password).matches())
            throw new ValidationExeption("Must contain uppercase letter.");

        return password;
    }

    public String validateName(String name) throws ValidationExeption {
        validateString(name);
        String[] names = name.split("\\s+");
        for (int i = 0; i < names.length - 1; i++) {
            names[i] = names[i] + " ";
        }
        //TODO !!!
        names = Arrays.stream(names)
                .filter(s -> s.length() > 1)
                .map(s -> s = s.substring(0,1).toUpperCase() + s.substring(1, s.length()).toLowerCase())
                .toArray(String[]::new);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(names).forEach(sb::append);
        return sb.toString();
    }

    public String validatePhonenumber(String phoneNumber) throws ValidationExeption {
        if (phoneNumber.length() != 10
                || (!(phoneNumber.startsWith(PHONE_NUMBER_PREFIX)))
                || (!(phoneNumber.matches("\\d+"))))
            throw new ValidationExeption(MESSAGE_PHONE_NUMBER);
        return phoneNumber;
    }

    public String validateEmail(String email) throws ValidationExeption {
        // TODO
        // java email package
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches())
            throw new ValidationExeption(MESSAGE_EMAIL);
        return email;
    }

    String exsistingString (String string) throws ValidationExeption {
        if (string == null) {
            throw new ValidationExeption(MESSAGE_NO_VALUE);
        }
        return string;
    }


}
