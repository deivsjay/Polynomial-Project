// Name: Divya Jagadeesh
// USC loginid: djagadee
// CS 455 PA2
// Fall 2016

import java.util.Scanner;
import java.util.ArrayList;

/**
   This class creates, prints, adds, and evaluates user inputed polynomials. A menu system is displayed for the user. This class uses 
   the polynomial class and interacts with the user. It represents a calculator for the polynomial class. Type menu command
   and polynomial array index to perform calculator functions on polynomial.
*/

public class PolynomialCalculator {
	
    public static void main(String args[]){

	int POLYSIZE = 10;	// user inputed numbers, and max size of polynomial
	Polynomial[] inputPoly = new Polynomial[POLYSIZE];	// polynomial being used 

	// initializes all elements in polynomial array to 0 polynomial
	for (int i = 0; i < POLYSIZE; i++) {
	    inputPoly[i] = new Polynomial();
	}
		
	// runs calculator functions
	runCalculator(inputPoly);
		
    }
	
	
    // **************************************************************
    //  PRIVATE METHOD(S)
	
	
    /*
     * Outputs a menu system so that the user can type a command to perform an action.
     */
    private static void menu(){
		
	System.out.println("HELP MENU");
	System.out.println("x represents the element in the Polynomial array we're working with");
	System.out.println("CREATE x - creates a polynomial x");
	System.out.println("PRINT x - prints the polynomial x");
	System.out.println("ADD x0 x1 x2 - polynomial x0 = polynomial x1 + polynomial x2");
	System.out.println("EVAL x - evaluates a polynomial x for a user inputted number");
	System.out.println("QUIT - exits program");
    }
	
    /*
     * Creates and returns a polynomial with the given polynomial the user wants to change. 
     */
    private static Polynomial doCreate(Polynomial create){

	create = new Polynomial();
	Scanner in = new Scanner(System.in);
	Scanner line;
	ArrayList<Double> input;
	ArrayList<Double> input2 = new ArrayList();
		
	System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by <nl>");

	if (in.hasNext()){
	    input = new ArrayList<Double>();
	    line = new Scanner(in.nextLine());
	    while (line.hasNextDouble()){
		input.add(line.nextDouble());
	    }
	    input2 = new ArrayList<Double>(input);
	}
		
	// getting rid of last exponent if user inputed odd number of integers
	if (input2.size() % 2 == 1){
	    input2.remove(input2.size() - 1);
	    System.out.println("WARNING: You forgot to enter the last exponent. The last coefficient will be deleted.");
	}
		
	// turns negative exponents positive
	for (int i = 1; i < input2.size(); i = i + 2){
	    if((input2.get(i) * -1) > 0){		
		input2.set(i, (input2.get(i) * -1));
		System.out.println("WARNING: Entered number " + (i + 1) + " is a negative exponent... turning positive.");
	    }
	}
		
	// creating double array to switch cases later
	double[] output = new double[input2.size()];
		
	// adding elements to double array
	for (int i = 0; i < input2.size(); i++){
	    output[i] = (double) input2.get(i);
	}
		
	// adding user inputed elements to user inputed index in polynomial array
	for (int i = 0; i < input2.size(); i = i + 2){
	    Polynomial addOn = new Polynomial(new Term((double) output[i], (int) output[i+1]));
	    create = create.add(addOn);

	}
		
	return create;	
    }
	
    /*
     * Prints the polynomial passed into the function.
     */
    private static void doPrint(Polynomial print){
	System.out.println(print.toFormattedString());
    }
	
    /*
     * Adds two of the input polynomials and returns the third resulting polynomial.
     */
    private static Polynomial doAdd(Polynomial firstPlusSecond, Polynomial firstPoly, Polynomial secondPoly){
	Polynomial sum = new Polynomial();
	sum = firstPoly.add(secondPoly);
	firstPlusSecond = sum;
	return firstPlusSecond;
    }
	
    /*
     * Evaluates the polynomial passed in for a given number.
     */
    private static void doEval(Polynomial eval){
	Scanner in = new Scanner(System.in);
	System.out.print("Enter a floating point value for x: ");
	double answer = in.nextDouble();
	answer = eval.eval(answer);
	System.out.println(answer);
    }
	
    /*
     * Makes sure passed index is within range, else outputs an error message.
     */
    private static boolean makeSureInIndex(int index){
	if(index < 0 || index > 9){
	    System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
	    return false;
	}
	return true;
    }
	
    /*
     * Runs functions of polynomial calculator with inputed polynomial.
     * Functions include: create, print, add, eval, and displays help menu. 
     */
    private static void runCalculator(Polynomial inputPoly[]){
		
	String userCommand;
	int userIndex, userIndex2, userIndex3;
	Scanner in = new Scanner(System.in);

	// begins program
	System.out.print("Type help to view the help menu, otherwise type desired commands: \ncmd> ");
		
	// takes user input
	userCommand = in.next();
		
	// runs as long as user doesn't input quit
	while (!(userCommand.equalsIgnoreCase("quit"))){
			
	    if(userCommand.equalsIgnoreCase("help")){
		menu();
	    }
	    else if(userCommand.equalsIgnoreCase("create")){
		userIndex = in.nextInt();	// takes user inputed number
		// if not in bounds, doesn't create polynomial
		if (makeSureInIndex(userIndex)){
		    inputPoly[userIndex] = doCreate(inputPoly[userIndex]);
		}
	    }
	    else if (userCommand.equalsIgnoreCase("print")){
		userIndex = in.nextInt();
		// doesn't print if not in bounds
		if (makeSureInIndex(userIndex)){
		    doPrint(inputPoly[userIndex]);
		}
	    }
	    else if(userCommand.equalsIgnoreCase("add")){
		userIndex = in.nextInt();
		userIndex2 = in.nextInt();
		userIndex3 = in.nextInt();
		// doesn't add if not in bounds
		if (makeSureInIndex(userIndex) && makeSureInIndex(userIndex2) && makeSureInIndex(userIndex3)){
		    inputPoly[userIndex] = doAdd(inputPoly[userIndex], inputPoly[userIndex2], inputPoly[userIndex3]);
		}
	    }
	    else if(userCommand.equalsIgnoreCase("eval")){
		userIndex = in.nextInt();
		// doesn't evaluate if not in bounds
		if (makeSureInIndex(userIndex)){
		    doEval(inputPoly[userIndex]);
		}
	    }
	    else{
		System.out.println("ERROR: Illegal command.  Type 'help' for command options.");
	    }
			
	    // continue taking commands
	    System.out.print("cmd> ");
	    userCommand = in.next();
	}
    }
}
