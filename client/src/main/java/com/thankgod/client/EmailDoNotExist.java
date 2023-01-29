package com.thankgod.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ ElementType.FIELD, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailDoNotExistValidator.class)
public @interface EmailDoNotExist {
  String message() default "Email already exists";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
