package chap07;

public class Main {

	
	public static void main( String[] args ) {
		
		CalculatorImpl cal = new CalculatorImpl();
		ExeTimeCalculator cal2 = new ExeTimeCalculator(cal);
//		RecCalculator cal = new RecCalculator();
		
		cal2.factorial( 5 );
	}
}
