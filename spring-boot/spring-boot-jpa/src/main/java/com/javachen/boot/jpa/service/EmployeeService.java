package com.javachen.boot.jpa.service;

import java.util.List;
import java.util.Optional;

import com.javachen.boot.jpa.exception.RecordNotFoundException;
import com.javachen.boot.jpa.model.Employee;
import com.javachen.boot.jpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id)  {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    @Transactional
    public Employee createOrUpdateEmployee(Employee entity)  {
        Optional<Employee> employee = repository.findById(entity.getId());

        if (employee.isPresent()) {
            Employee newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());

            newEntity = repository.save(newEntity);

            return newEntity;
        } else {
            entity = repository.save(entity);

            return entity;
        }
    }

    @Transactional
    public void deleteEmployeeById(Long id)  {
        Optional<Employee> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}