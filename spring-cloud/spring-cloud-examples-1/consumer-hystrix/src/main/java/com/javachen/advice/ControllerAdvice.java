package com.javachen.advice;

import com.javachen.controller.GreetingController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeoutException;

@RestControllerAdvice(assignableTypes = GreetingController.class)
@Slf4j
public class ControllerAdvice {
    @ExceptionHandler(TimeoutException.class)
    public Object faultToleranceTimeout(Throwable throwable) {

        log.info("ControllerAdvice#faultToleranceTimeout exception: {}", throwable.getMessage());

        return throwable.getMessage();
    }
}