package com.siemens.metal_forming.validators;

import com.siemens.metal_forming.annotations.MaxOneField;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class MaxOneFieldValidator implements ConstraintValidator<MaxOneField, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(MaxOneField maxOneField) {
        firstFieldName = maxOneField.first();
        secondFieldName = maxOneField.second();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext ctx) {
        try {
            final Object firstObj = getProperty(object, firstFieldName);
            final Object secondObj = getProperty(object, secondFieldName);

            return  !(firstObj != null && secondObj != null);
        } catch (final Exception ignore) {
            // ignore
        }
        return true;
    }

    private Object getProperty(Object value, String fieldName) {
        Field[] fields = value.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                field.setAccessible(true);
                try {
                    return field.get(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
