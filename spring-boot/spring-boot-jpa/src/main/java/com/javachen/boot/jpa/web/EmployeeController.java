package com.javachen.boot.jpa.web;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.javachen.boot.jpa.api.ApiError;
import com.javachen.boot.jpa.exception.RecordNotFoundException;
import com.javachen.boot.jpa.model.Employee;
import com.javachen.boot.jpa.service.EmployeeService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = List.class),
            @ApiResponse(code = 400, message = "Bad request", response = ApiError[].class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError[].class)
    })
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> list = service.getAllEmployees();
        return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 400, message = "Bad request", response = ApiError[].class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError[].class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee entity = service.getEmployeeById(id);
        return new ResponseEntity<Employee>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Employee.class),
            @ApiResponse(code = 400, message = "Bad request", response = ApiError[].class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError[].class)
    })
    @PostMapping
    public ResponseEntity<Employee> createOrUpdateEmployee(Employee employee) {
        Employee updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Void.class),
            @ApiResponse(code = 400, message = "Bad request", response = ApiError[].class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ApiError[].class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeById(@PathVariable("id") Long id) {
        service.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(err -> new ApiError(err.getCodes(), err.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<ApiError> handleValidationExceptions(ConstraintViolationException ex) {
        return ex.getConstraintViolations()
                .stream()
                .map(err -> new ApiError(err.getPropertyPath().toString(), err.getMessage()))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public List<ApiError> handleNotFoundExceptions(RecordNotFoundException ex) {
        return Collections.singletonList(new ApiError("employee.notfound", ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public List<ApiError> handleOtherException(Exception ex) {
        return Collections.singletonList(new ApiError(ex.getClass().getCanonicalName(), ex.getMessage()));
    }
}