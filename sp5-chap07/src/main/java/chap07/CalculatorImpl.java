package chap07;

public class CalculatorImpl implements Calculator {

	@Override
	public long factorial( long num ) {
		
		long result = 1;
		
		for ( long i = 1; i <= num; i++ ) {
			result *= i;
			System.out.println( "result *= i : " + result );
		}
		return result;
	}

}
