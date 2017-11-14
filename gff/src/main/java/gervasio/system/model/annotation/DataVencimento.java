/**
 * 
 */
package gervasio.system.model.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;

import gervasio.system.model.annotation.validator.DataVencimentoValidatorConstraint;

/**
 * @author Éderson Gervásio
 *
 */
@Constraint(validatedBy={DataVencimentoValidatorConstraint.class})
public @interface DataVencimento {

	String message() default "Data de vencimento não informada!";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
