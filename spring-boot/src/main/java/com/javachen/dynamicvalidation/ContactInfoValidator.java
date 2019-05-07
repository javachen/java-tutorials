package com.javachen.dynamicvalidation;

import com.javachen.dynamicvalidation.dao.ContactInfoExpressionRepository;
import com.javachen.dynamicvalidation.model.ContactInfoExpression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ContactInfoValidator implements ConstraintValidator<ContactInfo, String> {

    private static final Logger LOG = LogManager.getLogger(ContactInfoValidator.class);
    @Value("${contactInfoType}")
    String expressionType;
    @Autowired
    private ContactInfoExpressionRepository expressionRepository;
    private String pattern;

    @Override
    public void initialize(final ContactInfo contactInfo) {
        if (StringUtils.isEmpty(expressionType)) {
            LOG.error("Contact info type missing!");
        } else {
            pattern = expressionRepository.findById(expressionType).map(ContactInfoExpression::getPattern).orElse("");
        }
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(pattern)) {
            return Pattern.matches(pattern, value);
        }
        LOG.error("Contact info pattern missing!");
        return false;
    }

}
