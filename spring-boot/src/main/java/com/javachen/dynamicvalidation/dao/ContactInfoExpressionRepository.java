package com.javachen.dynamicvalidation.dao;

import com.javachen.dynamicvalidation.model.ContactInfoExpression;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ContactInfoExpressionRepository extends Repository<ContactInfoExpression, String> {
    Optional<ContactInfoExpression> findById(String id);
}
