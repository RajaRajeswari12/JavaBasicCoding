package com.fsd.JavaBuildingBlock.calc;



/**
 * Validation file is used to validate the given expression is complete or not.
 *
 */
public class Validation {
	/**
	 * validExpression method validate the given expression and if it is not in the 
	 * proper format then it ask the user to give a valid expressions.
	 * Valid Expression Format 
	 * (1+2)*3
	 * 2*5-3+9
	 * (12)(23),etc
	 *InValidExpressions
	 *
	 *2+3*
	 */
	boolean validExpression(String expression) {
		boolean validExp =false;
		if(expression.matches("(((\\()*([0-9]+[\\+\\-\\*\\/]{1}[0-9]+)*(\\))*)+([\\+\\-\\*\\/]{1}\\(*[0-9]+)*(\\(*[0-9]+\\)*)*([\\+\\-\\*\\/]{1}[0-9]+)*(\\))*)*")) {
			validExp = true;
		}

		return validExp;

	}	

}
