package com.github.terravivaproject.terraviva.resources.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * class
 *
 * @author Gianluigi De Marco
 * @version x
 * @since 29 09 2022
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {})
@Retention(RUNTIME)
@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")
public @interface UuidValidation {
    String message() default "{invalid.uuid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
