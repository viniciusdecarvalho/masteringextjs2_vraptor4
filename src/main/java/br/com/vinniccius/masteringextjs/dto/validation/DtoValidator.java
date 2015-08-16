package br.com.vinniccius.masteringextjs.dto.validation;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.caelum.vraptor.validator.ValidationException;
import br.com.caelum.vraptor.validator.Validator;
import br.com.vinniccius.masteringextjs.dto.DtoObject;

@SuppressWarnings("rawtypes")
public class DtoValidator implements
		ConstraintValidator<DtoValidation, DtoObject> {

	private final Validator validator;
	
	protected DtoValidator() {
		this(null);
	}
	
	@Inject
	public DtoValidator(Validator validator) {
		this.validator = validator;
	}
	
	@Override
	public void initialize(DtoValidation annotation) {
	}

	@Override
	public boolean isValid(DtoObject value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}
		Object model = value.getModel();
		validator.validate(model);
		if (validator.hasErrors()) {
			throw new ValidationException(validator.getErrors());
		}
		return true;
	}

}