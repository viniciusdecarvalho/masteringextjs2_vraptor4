package br.com.vinniccius.masteringextjs.dto.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DtoValidator.class, DtoCollectionValidator.class })
@Documented
public @interface DtoValidation {

	String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
	
}
