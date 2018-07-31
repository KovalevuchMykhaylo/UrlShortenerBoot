package com.shortener.validators;

import com.shortener.dto.CreateCustomUrlDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CustomShortUrlDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateCustomUrlDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CreateCustomUrlDto createCustomUrlDto = (CreateCustomUrlDto) target;

        if (errors.getFieldError("longUrl") == null) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "longUrl", "label.cant_be_empty");
        }

        if (createCustomUrlDto.getLongUrl() != null) {
            String longUrl = createCustomUrlDto.getLongUrl().trim();
            if (!longUrl.startsWith("https://") & !longUrl.startsWith("http://"))
                errors.rejectValue("longUrl", "label.bat_url");
        }

    }

}
