package com.fsd.JavaBuildingBlock.calc;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {


	/**
	 * calculateExp() method first call the postFixConversion method to convert
	 * the given infix expression into postfix expression. Then Calls the ResultCalculation
	 * method to evaluate the final result.
	 * */
	double calculateExp(String expression) {
		double finalValue = 0;
		Stack<String> postFixExp = new Stack<String>();
		PostFixConversion pf = new PostFixConversion();
		postFixExp = pf.postFixConversion(expression);

		System.out.println(postFixExp);
		ResultCalculation.postFixToResult(postFixExp);
		return finalValue;
	}
	
	/**
	 * main() method is used to get the expression from the user 
	 * through console. After that it calls validExpression method to validate
	 * whether the given expression is valid or not.If valid it proceeds to the next step
	 * otherwise again it asks the user to enter the valid expressions.
	 * */
	public static void main(String[] args) {
		boolean validData = false;
		Scanner inputReader = new Scanner(System.in);
		System.out.println("Enter the Arthmetic Expression to be calculated");
		
		while(!validData) {
			String expression = inputReader.next();
			Calculator c = new Calculator();
			Validation validTest = new Validation();
			
			boolean validExp = validTest.validExpression(expression);
			if(validExp) {				
				c.calculateExp(expression);
				validData = true;
			}else {
				System.out.println("Enter valid Expression to be Calculated: (Allows numbers,+,*,-,/,(,))");
			}
		}
	}
}
