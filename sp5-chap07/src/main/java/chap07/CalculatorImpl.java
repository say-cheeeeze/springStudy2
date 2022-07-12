package chap07;

public class CalculatorImpl implements Calculator {

	/**
	 * 계승값 구하기양의 정수 n 의 계승은 n! 으로 표현하며
	 * 1 부터 n 까지 숫자의 곱을 의미한다.
	 * 예를 들어 4! 의 값은 4*3*2*1 의 결과인 24 가 된다.
	 */
	@Override
	public long factorial( long num ) {
		
		long startTime = System.currentTimeMillis();
		
		long result = 1;
		
		for ( long i = 1; i <= num; i++ ) {
			result *= i;
//			System.out.println( "Calculator.java ...result *= i : " + result );
		}
		
		long endTime = System.currentTimeMillis();
		
//		System.out.println( "Calculator.java ...걸린 시간 : " + ( endTime - startTime ) );
		return result;
	}

}
