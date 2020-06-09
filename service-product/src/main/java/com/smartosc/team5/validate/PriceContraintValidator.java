package com.smartosc.team5.validate;

import com.smartosc.team5.validate.annotation.ValidPrice;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * team5
 *
 * @author ThaoPhuong
 * @created_at 05/06/2020 - 05:34 PM
 * @created_by ThaoPhuong
 * @since 05/06/2020
 */
public class PriceContraintValidator implements ConstraintValidator<ValidPrice, Double> {
    @Override
    public void initialize(ValidPrice constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && (value > 0 && value < 99999999);
    }
}
