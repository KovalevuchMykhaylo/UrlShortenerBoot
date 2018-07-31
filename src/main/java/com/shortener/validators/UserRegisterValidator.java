package com.shortener.validators;

import com.shortener.dto.UserRegisterDto;
import com.shortener.service.UserService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

import static com.shortener.constants.Constants.*;

public class UserRegisterValidator implements Validator {

    private final static Pattern REG_NAME = Pattern.compile(USER_NAME);
    private final static Pattern REG_PASS = Pattern.compile(USER_PASSWORD);

    private final UserService userService;

    public UserRegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegisterDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserRegisterDto userRegisterDto = (UserRegisterDto) target;
        if (errors.getFieldError("name") == null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "label.name_cant_be_empty");
        }

        if (errors.getFieldError("password") == null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "label.password_cant_be_empty");
        }

        if (!(userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword()))) {
            errors.rejectValue("confirmPassword", "label.confirm_password");
        }

        if (!REG_NAME.matcher(userRegisterDto.getName()).matches()) {
            errors.rejectValue("name", "label.user_name");
        }

        if (!REG_PASS.matcher(userRegisterDto.getPassword()).matches()) {
            errors.rejectValue("password", "label.bat_user_password");
        }

        if (null != userService.findByUserName(userRegisterDto.getName())) {
            errors.rejectValue("name", "label.name_already_exists");
        }
    }
}
