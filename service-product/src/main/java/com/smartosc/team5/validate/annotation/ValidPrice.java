package com.smartosc.team5.validate.annotation;

import com.smartosc.team5.validate.PriceContraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

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
@Documented
public @interface ValidPrice {
    String message() default "Invalid price";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}