package com.thankgod.client;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailDoNotExistValidator implements ConstraintValidator<EmailDoNotExist, String> {
  @Override
  public boolean isValid(String email, ConstraintValidatorContext cvc) {
    return true;
  }
}
