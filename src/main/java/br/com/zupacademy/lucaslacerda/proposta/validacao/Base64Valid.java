package br.com.zupacademy.lucaslacerda.proposta.validacao;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = Base64Validator.class)
public @interface Base64Valid {
	
	String message() default "Biometria deve ser informada na BASE64."; 
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {}; 
    
}