/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		// Welcome statement prompting user to enter expression
		System.out.println("Welcome to the fraction calculator");
		System.out.println("Type an expression (if you enter mixed fractions, it should be like 1_1/2) OR type \"Quit\" to exit:");
		Scanner output = new Scanner(System.in);
		
		
		String userInput = output.nextLine();
		
		// If user wants to exit FracCalc, they can type "quit"
	while(!userInput.equalsIgnoreCase("Quit")) {
		
			System.out.println(produceAnswer(userInput));
			System.out.println("Type an expression (if you enter mixed fractions, it should be like 1_1/2) OR type \"Quit\" to exit:");
	       	userInput = output.nextLine();

	}
	// Once "quit" is typed, the user is exited out of FracCalc program and it will thank the user for trying it
	output.close();
	System.out.println("Thanks for trying FracCalc!");
	    }
	

    public static String produceAnswer(String input){
    	// finds the values based on the spaces so if there are no spaces, the calculator returns error statement
    	int spacing = input.indexOf(' ');
		if (spacing < 0)
		{
			return ("ERROR: That's not a statement. Must have spaces between numbers and operators.");
		}
		else
		{
			// gets values and operators for the calculator to calculate
			String operLeft;
			String operRight;
			int numLeft;
			int numMiddle;
			int numRight;
			int denLeft;
			int denMiddle;
			int denRight;
			
			String token = getToken(input);
			input = removeToken(input);
			parseFraction(token);
			numLeft = numOutput;
			denLeft = denOutput;
			
			token = getToken(input);
			input = removeToken(input);
			operLeft = token;
			
			token = getToken(input);
			input = removeToken(input);
			parseFraction(token);
			numMiddle = numOutput;
			denMiddle = denOutput;
			
			// goes through extra operations 
			boolean calcWorks = false;
			boolean operMore = true;
			
			
			while (operMore)
			{
				if (input.length() <= 1)
				{
					// Once there are no more operators, then start calculating what is inputed
					operMore = false;
					calcWorks = calculate(numLeft, denLeft, numMiddle, denMiddle, operLeft);
				}
				else
				{
					// Get next operator
					token = getToken(input);
					input = removeToken(input);
					
					operRight = token;
					
					// Get next fraction
					token = getToken(input);
					input = removeToken(input);
					parseFraction(token);
					numRight = numOutput;
					denRight = denOutput;
					
					// Goes through each operation to check which to process first
					if ((operLeft.equals("+") || (operLeft.equals("-"))) 
							&& (operRight.equals("*") || operRight.equals("/")))
					{
						// Do the right operator first because the right has precedence
						calcWorks = calculate(numMiddle, denMiddle, numRight, denRight, operRight);
						// Save the answer as the new middle fraction
						numMiddle = numOutput;
						denMiddle = denOutput;
					}
					else
					{
						// Do the left operator first in this case because left has higher/equal precedence
						calcWorks = calculate(numLeft, denLeft, numMiddle, denMiddle, operLeft);
						// Save the answer as the new left fraction 
						numLeft = numOutput;
						denLeft = denOutput;
						operLeft = operRight;
						// The right fraction becomes the middle
						numMiddle = numRight;
						denMiddle = denRight;
					}
				}
			}
			// Return error statement if inputs do not follow the correct format
			if (calcWorks)
			{
				return printFraction(numOutput, denOutput);
			}
			else 
			{
				return ("ERROR: Input is in an invalid format. SUGGESTION: Denominator must not = 0 (ex. 1/0) and there can only be one operator between values (ex. 1 ++ 2).");
			}
		}
	}
		
    // static methods used for parsing 
	public static String removeToken(String input)
	{
		int space = input.indexOf(' ');
		if (space == -1)
			return "";
		return input.substring(space + 1);
	}

	public static String getToken(String input)
	{
		int space = input.indexOf(' ');
		if (space == -1)
			return input;
		return input.substring(0, space);
	}

	// setting the variables to store "parseFraction()" or "calculate()" results
	static int numOutput;
	static int denOutput;

	// Parsing a fraction string
	public static void parseFraction(String input)
	{
		// parse left fraction
		int underscore = input.indexOf('_');
		int slash = input.indexOf('/');
		if (underscore > 0)
		{
			// There is an underscore, so it knows that it is a mixed number.
			int whole = Integer.parseInt(input.substring(0, underscore));
			numOutput = Integer.parseInt(input.substring(underscore + 1, slash));
			// The negative applies to the fraction too if the whole thing is negative
			if (whole < 0)
				numOutput = 0 - numOutput;
			denOutput = Integer.parseInt(input.substring(slash + 1));
			numOutput = numOutput + whole * denOutput;
		}
		else if (slash > 0)
		{
			// There is a '/', so this is a fraction.
			numOutput = Integer.parseInt(input.substring(0, slash));
			denOutput = Integer.parseInt(input.substring(slash + 1));
		}
		else
		{
			// This is a whole number.
			numOutput = Integer.parseInt(input);
			denOutput = 1;
		}
	}

	// Calculate fractions but return false if inputs don't follow rules
	public static boolean calculate(int firstNum, int firstDen, int secondNum, int secondDen, String operator)
	{
		if (operator.equals("+"))
		{
			numOutput = firstNum * secondDen + secondNum * firstDen;
			denOutput = firstDen * secondDen;
		}
		else if (operator.equals("/"))
		{ 
		// Denominator cannot be zero or it will return error message
			if (secondNum==0) 
		{
			return false;
		}
			numOutput = firstNum * secondDen;
			denOutput = firstDen * secondNum;
		}
		
		else if (operator.equals("*"))
		{
			numOutput = firstNum * secondNum;
			denOutput = firstDen * secondDen;
		}
		
		else if (operator.equals("-"))
		{
			numOutput = firstNum * secondDen - secondNum * firstDen;
			denOutput = firstDen * secondDen;
		}
	
		else
		{
			return false;
		}
		return true;
	}

	// Have a simplified fraction
	public static String printFraction(int num, int den)
	{
		String result = "";

		// Make sure the denominator is positive
				if (den < 0)
				{
					den = 0 - den;
					num = 0 - num;	
				}		
				
		if (num < 0)
		{
		// If the numerator is negative then print the negative sign at the front of the number
			result += "-";
			num = 0 - num;
		}
		// simplifying by finding the greatest common denominator
		int gcd = findGreatComDen(num, den);
		num = num / gcd;
		den = den / gcd;

		// Simplifying to a mixed number
		int whole = num / den;
		num = num % den;

		// Print out the answer
		if (num == 0)
		{
		// Print out whole thing since there's no fraction
			result += whole;
		}
		else
		{
			if (whole > 0)
			{
				// But if there is a fraction print it too 
				result += (whole + "_");
			}
			// Prints the fraction
			result += (num + "/" + den);
		}
		return result;
	}
	// Returns the greatest common denominator
	
	public static int findGreatComDen(int i, int j)
	{
		while (true) {
			if (i < j) {
				int k = i;
				i = j;
				j = k;
			}
			if (j == 0)
				return i;
			i = i % j;
		}
	}

}		
