package com.smartosc.team5.validate;

import com.smartosc.team5.validate.annotation.ValidPrice;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Team5
 *
 * @author Huupd
 * @created_at 04/06/2020 - 2:17 PM
 * @created_by Huupd
 */
public class PriceContraintValidator implements ConstraintValidator<ValidPrice,Integer> {


    @Override
    public void initialize(ValidPrice constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value != null && (value > 0 && value < 99999999);
    }
}
