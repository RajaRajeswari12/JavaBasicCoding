package com.fsd.JavaBuildingBlock.calc;

import java.util.Stack;

public class ResultCalculation {
	static double value1;
	static double value2;
	static boolean isValue2Null = false;  
	static Stack<Double> resultStack = new Stack<Double>(); 
/*	public static void setResultStack(Stack<Double> resultStack) {
		ResultCalculation.resultStack = resultStack;
	}*/

	/**
	 * postFixToResult() method is evaluate the postfixExpression and returns the final result.ss
	 * */
	static double postFixToResult(Stack<String> postFixExp) {
//		Stack<Double> resultStack = new Stack<Double>(); 

		double result;
		for(int index=0;index < postFixExp.size();index++){
			String stackVal = postFixExp.get(index);
			switch(stackVal) {			
			case "+":
				setValues();
				result = value1 + value2;
				resultStack.push(result);
//				setResultStack(resultStack);
				break;
			case "*":
				setValues();
				result = value1 * value2;
				resultStack.push(result);
//				setResultStack(resultStack);
				break;
			case "-":
				setValues();				
				result = value1 - value2;
				resultStack.push(result);
//				setResultStack(resultStack);
				break;
			case "/":
				setValues();
				result = value1 / value2;
				resultStack.push(result);
//				setResultStack(resultStack);
				break;

			default :
				resultStack.push(Double.parseDouble(stackVal));
//				setResultStack(resultStack);
				break;
			}
		}

		System.out.println("Final Value"+ resultStack);
		return resultStack.pop();
	}

	static void setValues() {
		
		value2 = resultStack.pop();
		if(!resultStack.isEmpty()) {
			value1 = resultStack.pop();			
		}else {
			value1 = 0;
		}
	}

}
