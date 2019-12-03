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
		
		
		while(!fraction.equals("quit")) {
		
		System.out.println(produceAnswer(fraction));
		System.out.println("Welcome to the fraction calculator");
		System.out.println("Enter the values you want to be calculated");
		fraction = firstInput.nextLine();

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
		public static String produceAnswer(String input){
		String frac1 = input.substring(0, input.indexOf(" "));
        	String operator = input.substring(input.indexOf(" ") + 1, input.indexOf(" ") + 2);
        	String frac2 = input.substring(input.indexOf(" ") + 3);
       		String whole1 = frac1;
        	String num1 = "0";
        	String denom1 = "1";
        		if (frac1.indexOf("/") != -1){
          		if (frac1.indexOf("_") != -1){
           		 whole1 = frac1.substring(0, frac1.indexOf("_"));
         		 }else{
          		  whole1 = "0";
          	}
          num1 = frac1.substring(frac1.indexOf("_") + 1, frac1.indexOf("/"));
          denom1 = frac1.substring(frac1.indexOf("/") + 1, frac1.length());
        }
        String whole2 = frac2;
        String num2 = "0";
        String denom2 = "1";
        if (frac2.indexOf("/") != -1){
          if (frac2.indexOf("_") != -1){
            whole2 = frac2.substring(0, frac2.indexOf("_"));
          }else{
            whole2 = "0";
          }
          num2 = frac2.substring(frac2.indexOf("_") + 1, frac2.indexOf("/"));
          denom2 = frac2.substring(frac2.indexOf("/") + 1, frac2.length());
        }
        int result = Integer.valueOf(num2);
        return result;
      }
  }
		
		// TODO: Implement this function to produce the solution to the input
		
		
		
		
