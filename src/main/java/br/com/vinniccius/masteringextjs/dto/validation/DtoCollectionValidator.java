package br.com.vinniccius.masteringextjs.dto.validation;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.Validator;
import br.com.vinniccius.masteringextjs.dto.DtoObject;

@SuppressWarnings("rawtypes")
public class DtoCollectionValidator 
		implements ConstraintValidator<DtoValidation, Collection<? extends DtoObject>> {

	private final Validator validator;
	
	protected DtoCollectionValidator() {
		this(null);
	}
	
	@Inject
	public DtoCollectionValidator(Validator validator) {
		this.validator = validator;
	}
	
	@Override
	public void initialize(DtoValidation constraintAnnotation) {
	}

	@Override
	public boolean isValid(Collection<? extends DtoObject> value,
			ConstraintValidatorContext context) {
		
		if (value == null) {
			return true;
		}
		
		for (DtoObject dto : value) {
			validator.validate(dto.getModel());
		}
		
		if (validator.hasErrors()) {
			throw new ValidationException(validator.getErrors());
		}
		
		return true;
	}

}
