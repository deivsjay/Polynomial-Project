
public class PolynomialTester {
	
	public static void main(String args[]){
		
		Polynomial poly1 = new Polynomial();
		System.out.println(" 1. " + poly1.toFormattedString());
		double evaluation = poly1.eval(3.5);
		System.out.println("1. evaluated for x = 3.5: " + evaluation);
		
		Polynomial poly2 = new Polynomial(new Term(3,5));
		System.out.println(" 2. " + poly2.toFormattedString());
		
		Polynomial poly3 = new Polynomial(new Term(4,6));
		Polynomial poly4 = new Polynomial();
		poly4 = poly2.add(poly3);
		System.out.println(" 3. " + poly4.toFormattedString());
		
		Polynomial poly5 = new Polynomial(new Term(1,2));
		Polynomial poly6 = new Polynomial(new Term(3,4));
		poly5 = poly5.add(poly6);
		System.out.println(" 4. " + poly5.toFormattedString());
		
		Polynomial poly7 = new Polynomial();
		Polynomial poly8 = new Polynomial(new Term(1,2));
		poly7 = poly7.add(poly8);
		System.out.println(" 5. " + poly7.toFormattedString());
		
		Polynomial poly9 = new Polynomial(new Term(1,2));
		Polynomial poly10 = new Polynomial(new Term(2,3));
		Polynomial poly11 = poly9.add(poly10);
		System.out.println(" 6. " + poly11.toFormattedString());
		
		double evaluation2 = poly11.eval(3);
		System.out.println("6. evaluated for x = 3: " + evaluation2);
		double evaluation3 = poly11.eval(3.4);
		System.out.println("6. evaluated for x = 3.5: " + evaluation3);
		
		Polynomial poly12 = new Polynomial(new Term(0,4));
		Polynomial poly13 = poly12.add(poly10);
		System.out.println("7. " + poly13.toFormattedString());
		
		Polynomial p5 = new Polynomial(new Term(3, 2)).add(new Polynomial(new Term(-5, 2)));
		System.out.println("8. " + p5.toFormattedString());
		
	}

}

