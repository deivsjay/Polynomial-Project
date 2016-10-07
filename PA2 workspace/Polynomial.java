// Name: Divya Jagadeesh
// USC loginid: djagadee
// CS 455 PA2
// Fall 2016

import java.util.ArrayList;

/**
   A polynomial. Polynomials can be added together, evaluated, and
   converted to a string form for printing.
   
   Representation Invariant:
-- all elements in polynomial are terms (coefficient and exponent pair)
-- for a non-zero polynomial all the terms must be in decreasing order by exponent
-- exponents >= 0
-- don't have two+ terms with the same exponent
-- if the polynomial equals 0, a polynomial object has no elements in the ArrayList

*/
public class Polynomial {
	
    /** 
       Function: Creates the 0 polynomial
    */
    public Polynomial() {
    	
    	myPoly = new ArrayList<Term>();
    	isValidPolynomial();
    }


    /**
       Creates polynomial with single term given. 
     */
    public Polynomial(Term term) {
    	
    	myPoly = new ArrayList<Term>();
    	myPoly.add(term);
    	isValidPolynomial();

    }


    /**
       Returns the Polynomial that is the sum of this polynomial and b
       (neither polynomial is modified), given b.
       PRECONDITION: No zero coefficient terms in either polynomials
     */
    
    public Polynomial add(Polynomial b) {
    	
    	// gets rid of zeros that may exist in polynomial being added 
    	b.removeZeros();
    	this.removeZeros();
    	
    	// makes sure the polynomials are valid
    	assert isValidPolynomial();
    	assert b.isValidPolynomial();
    	
    	
    	// starts iterators for both polynomials at 0
    	int bSize = 0, mySize = 0;
    	
    	ArrayList<Term> bTerms = b.myPoly;		// ArrayList of b's terms
    	ArrayList<Term> sum = new ArrayList<Term>();	// ArrayList of summations
    	
    	// keeps going as long as either iterator isn't the size of the ArrayList
    	while(bSize < bTerms.size() || mySize < myPoly.size()){
    		// if b has a bigger exponent, adds b
			if(bSize < bTerms.size() && mySize < myPoly.size() && bTerms.get(bSize).getExpon() > myPoly.get(mySize).getExpon()){
				sum.add(bTerms.get(bSize));
				bSize++;
			}
			// if polynomial that called the add has a bigger exponent, adds poly
			else if (bSize < bTerms.size() && mySize < myPoly.size() && myPoly.get(mySize).getExpon() > bTerms.get(bSize).getExpon()){
				sum.add(myPoly.get(mySize));
				mySize++;
			}
			// if they both have same exponent, add coefficients 
			else if (bSize < bTerms.size() && mySize < myPoly.size()) {
				Term coeffSumWithSameExp = new Term(myPoly.get(mySize).getCoeff() + bTerms.get(bSize).getCoeff(), myPoly.get(mySize).getExpon());
				sum.add(coeffSumWithSameExp);
				bSize++;
				mySize++;
			}
    		// if b's reached it's max size or b has no size, add rest of poly that called function
    		else if ((bSize == bTerms.size() && mySize < myPoly.size()) || (bTerms.size() == 0 && mySize < myPoly.size())){
    			sum.add(myPoly.get(mySize));
    			mySize++;
    		}
    		// // if poly's reached it's max size or poly has no size, add rest of b
    		else if ((bSize < bTerms.size() && mySize == myPoly.size()) ||( myPoly.size() == 0 && bSize < bTerms.size())){
    			sum.add(bTerms.get(bSize));
    			bSize++;
    		}
    	}
    	
    	// puts term elements into new polynomial
    	Polynomial returnPoly = new Polynomial();
    	returnPoly.myPoly = sum;
    	
    	
    	// removes zeros if any and checks if it's a valid polynomial
    	returnPoly.removeZeros();
    	assert returnPoly.isValidPolynomial();
    	
    	return returnPoly;  // dummy code.  just to get stub to compile
    }


    /**
       Returns the value of the polynomial at a given value of x.
       PRECONDITION: No zero coefficient terms in polynomial.
     */
    public double eval(double x) {
    	
    	assert isValidPolynomial();
    	double evaluation = 0.0;
 
    	if (myPoly.size() == EMPTY_POLY){	// if polynomial is zero polynomial, return 0.0 for evaluation
    		return evaluation;
    	}
    	else{	// else input given number into polynomial 
    		for (int i = 0; i < myPoly.size(); i++){
    			evaluation += myPoly.get(i).getCoeff() * Math.pow(x, (double) myPoly.get(i).getExpon());
    		}
    	}
    	
	return evaluation;         
    }


    /**
       Return a String version of the polynomial with the 
       following format, shown by example:
       zero poly:   "0.0"
       1-term poly: "3.2x^2"
       4-term poly: "3.0x^5 + -x^2 + x + -7.9"

       Polynomial is in a simplified form (only one term for any exponent),
       with no zero-coefficient terms, and terms are shown in
       decreasing order by exponent.
       
       PRECONDITION: No zero coefficient terms in polynomial.
    */
    public String toFormattedString() {
    	
    	assert isValidPolynomial();
    	String returnStringPoly = ""; 
    	
    	// if nothing exists in the polynomial, return empty string
    	if (myPoly.size() == 0){
    		returnStringPoly = EMPTY_STRING;
    	} 
    	else {	// if there are elements in the polynomial, return printed string
    		for (int i = 0; i < myPoly.size(); i++){
    			
    			if (myPoly.get(i).getCoeff() == -1.0){
    				returnStringPoly += "-";
    			}
    			else if(myPoly.get(i).getCoeff() == 1.0){
    				if (myPoly.get(i).getExpon() == ZERO_EXP){
    					returnStringPoly += "1.0";
    				}
    				returnStringPoly += "";
    			}
    			else {
    				returnStringPoly += myPoly.get(i).getCoeff();
    			}
    			
    			if(myPoly.get(i).getExpon() == 1){
    				returnStringPoly += "x";
    			}
    			else if(myPoly.get(i).getExpon() == ZERO_EXP){
    				returnStringPoly += "";
    			}
    			else {
    				returnStringPoly += "x^" + myPoly.get(i).getExpon();
    			}
    			
    			if (i < myPoly.size() - 1){
    				returnStringPoly += " + ";
    			}
    		}
    	}

    	return returnStringPoly;
    }


    // **************************************************************
    //  PRIVATE METHOD(S)

    /**
       Returns true iff the poly data is in a valid state.
    */
    private boolean isValidPolynomial() {
    	
    	boolean isValid = true;
    	
    	// doesn't throw exception if size = 0
    	if (myPoly.size() == EMPTY_POLY){
    		return true;
    	}
    	
    	// throws exception if terms later on in the polynomial ArrayList have higher exponent terms
    	for (int i = 0; i < myPoly.size() - 1; i++){
    		if (myPoly.get(i).getExpon() <= myPoly.get(i+1).getExpon() || myPoly.get(i).getCoeff() == ZERO){
    			isValid = false;
    		}
    	} 
    	if(myPoly.get(myPoly.size()-1).getCoeff() == ZERO){
    		isValid = false;
    	}
    	
    	return isValid;
    }
    
    /*
     * Gets rid of elements with zero coefficients in the polynomial.
    */
    private void removeZeros(){
    	
    	for (int i = myPoly.size() - 1; i >= 0; i--){
    		if(myPoly.get(i).getCoeff() == ZERO){
    			myPoly.remove(i);
    		}
    	}
    }

    // **************************************************************
    //  PRIVATE INSTANCE VARIABLE(S)
   
	private ArrayList<Term> myPoly;					// stores individual terms in ArrayList
	private final int EMPTY_POLY = 0;				// no elements in polynomial
	private final double ZERO = 0.0;				// for recognizing 0 coefficient
	private final String EMPTY_STRING = "0.0";		// for printing empty polynomials
	private final int ZERO_EXP = 0;					// for recognizing 0 exponent
  
}
