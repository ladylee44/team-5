package com.smartosc.common.validate;

import com.smartosc.common.validate.annotation.ValidPrice;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Team5
 *
 * @author Huupd
 * @created_at 04/06/2020 - 2:17 PM
 * @created_by Huupd
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
