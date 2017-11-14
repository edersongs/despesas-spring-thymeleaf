/**
 * 
 */
package gervasio.system.model.annotation.validator;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import gervasio.system.model.annotation.DataVencimento;

/**
 * @author Éderson Gervásio
 *
 */
public class DataVencimentoValidatorConstraint implements ConstraintValidator<DataVencimento, Date> {

	@Override
	public void initialize(DataVencimento constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
