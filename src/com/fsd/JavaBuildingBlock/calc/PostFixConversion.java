package com.fsd.JavaBuildingBlock.calc;

import java.util.Stack;

public class PostFixConversion {

	Stack<String> postFix = new Stack<String>();
	char[] splitExp;
	int expLength ;
	char currOperator;
	char prevOperator;
	
	/**
	 * setCurrOperator() method is used in the case of Plus & Minus. To achieve Bodmas rule.
	 * It holds the latest operator value .
	 * */
	public void setCurrOperator(char currOperator) {
		this.currOperator = currOperator;
	}

	/**
	 * setPrevOperator() method is used in the case of Plus & Minus. To achieve Bodmas rule.
	 * It holds the previous operator value .
	 * */
	public void setPrevOperator(char prevOperator) {
		this.prevOperator = prevOperator;
	}
	
	/**
	 * postFixConversion is the method from which the expression conversion to PostFix starts.
	 * It splits the expression into char array and starts the process. Finally it recieves the postFixExpression stack.
	 * */
	Stack<String> postFixConversion(String expression){
		splitExp = expression.toCharArray();	
		expLength = splitExp.length -1;
		for(int i=0;i<=expLength ;i++) {
			char value = splitExp[i];
			if(!Character.isDigit(value)) {
				
				i = operationSwitch(value, i);
			}else if(i != expLength){
				i = toAddNumbers(i);
			}
		}
		return postFix;
	}

	/**
	 * operationSwitch() method is used to achieve Bodmas rule.
	 * 
	 * */
	int operationSwitch(char value, int index) {
		switch(value) {
		case '+':
			if(currOperator == '+' && prevOperator == '-') {
				postFix.push("-");
				index= plusMinusOperator(index);

			}else {
				index= plusMinusOperator(index);				
			}

			if(prevOperator != '-') {
				postFix.push("+");
			}

			break;

		case '-':
			if(prevOperator == '+' && currOperator == '-') {
				postFix.push("+");
				index= plusMinusOperator(index);

			}else {
				index = plusMinusOperator(index);

			}

			if(prevOperator != '+') {
				postFix.push("-");
			}
			break;						

		case '*':
			index = multiplicationDivisionOperator(index);
			postFix.push("*");
			break;

		case '/':
			index = multiplicationDivisionOperator(index);
			postFix.push("/");
			break;

		case '(':
			int prevIndex = index;
			index = withInBracket(index);
			if(prevIndex != 0 && (Character.isDigit(splitExp[prevIndex-1]) || splitExp[prevIndex-1] == ')')) {
				postFix.push("*");
			}
			break;
		}		
		return index;
	}

	/**
	 * plusMinusOperator() method is used to achieve Bodmas rule. If operators like multiplication
	 * and division occurs after plus/Minus then priority is given to (*,/) operators. 
	 * 
	 * */
	int plusMinusOperator(int index) {
		setPrevOperator(splitExp[index]);

		if(index < expLength) { 
			if(Character.isDigit(splitExp[index+1])) {
				index = toAddNumbers(index+1);
			}
			++index;
			if(index < expLength) {
				char value = splitExp[index];	
				setCurrOperator(splitExp[index]);
				index = operationSwitch(value, index);
			}
		}
		return index;
	}

	/**
	 * multiplicationDivisionOperator() method is used to add the operand next to this
	 * operators and then add the (*,/) operator to achieve postfix expression. 
	 * */
	int multiplicationDivisionOperator(int indexVal) {
		
		if(Character.isDigit(splitExp[indexVal+1])) {
			indexVal = toAddNumbers(indexVal+1);
		}else if((indexVal < expLength) && (splitExp[indexVal+1] == '(')){
			indexVal = withInBracket(indexVal+1);
		}
		return indexVal;
	}


	/**
	 * withInBracket() method is used to convert the expression inside the bracket 
	 * to achieve postfix expression. 
	 * */
	int withInBracket(int index) {
		if(index < expLength) {
			char value = splitExp[index];
			while(value != ')') {
				index++;
				if(index < expLength) {
					if( Character.isDigit(splitExp[index])) {
						index = toAddNumbers(index);
					}
					value = splitExp[index];	
					index = operationSwitch(value, index);
				}else {
					return index;
				}	
			}	
		}
		return index;
	}
	
	/**
	 * toAddNumbers() method is used to read the char array and concat it
	 * till there are numbers. If it hits the first operator then it push the numbers
	 * into the PostFix Stack.
	 * */
	int toAddNumbers(int indexNo) {
		String numberConcat = "";		
		if(indexNo <= expLength) {
			char value = splitExp[indexNo];			
			while(Character.isDigit(value)) {
				numberConcat += String.valueOf(value);
				if(indexNo < expLength) {
					++indexNo;
					value = splitExp[indexNo];
				}else if(indexNo == expLength) {
					postFix.push(numberConcat);
					return indexNo;
				}
			}
			postFix.push(numberConcat);
		
			indexNo = indexNo-1;
		}
		return indexNo;
	}	
}
