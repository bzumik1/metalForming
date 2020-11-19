package com.siemens.metal_forming.annotations;

import com.siemens.metal_forming.validators.MaxOneFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MaxOneFieldValidator.class})
public @interface MaxOneField {
    String message() default "Only one field can be not null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    /**
     * @return The first field
     */
    String first();

    /**
     * @return The second field
     */
    String second();

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        MaxOneField[] value();
    }
}
