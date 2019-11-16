/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		Scanner firstInput = new Scanner(System.in);
		System.out.println("Welcome to the fraction calculator");
		System.out.println("Enter the values you want to be calculated");
		String fraction = firstInput.nextLine();
		System.out.println(produceAnswer(fraction));

		// TODO: Read the input from the user and call produceAnswer with an equation

	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input
		Scanner math = new Scanner(input);
		String firstOperand = math.next();
		String operator = math.next();
		String secondOperand = math.next();
		return secondOperand;

	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need

}