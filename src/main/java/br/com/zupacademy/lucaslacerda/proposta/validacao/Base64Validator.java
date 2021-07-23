package br.com.zupacademy.lucaslacerda.proposta.validacao;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class Base64Validator implements ConstraintValidator<Base64Valid, Object> {

		
	
	@Override
	public void initialize(Base64Valid constraintAnnotation) {
				     
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return (value instanceof String && 
				((String) value).length()>0 &&
				((String) value).matches("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{4})$"));
	}

}
