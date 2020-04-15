package com.javachen.boot.data.rest.validator;

import com.javachen.boot.data.rest.entity.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotNull", "书名不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "NotNull", "作者不能为空");
    }
}