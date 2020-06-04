package com.smartosc.team5.validate.annotation;

import com.smartosc.team5.validate.PriceContraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Team5
 *
 * @author Huupd
 * @created_at 04/06/2020 - 2:18 PM
 * @created_by Huupd
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriceContraintValidator.class)
public @interface ValidPrice {
    String message() default "Invalid price";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
