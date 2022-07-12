package chap07;

public class RecCalculator implements Calculator {

	/**
	 * 재귀 호출을 이용하여 계승을 구한다.
	 */
	@Override
	public long factorial( long num ) {
		
		
//		System.out.println("파라미터 : " + num );
		// 수학적 논리에 의해 0 팩토리얼은 1 이 된다.
		// 0! = 1
		if ( num == 0 ) {
			return 1;
		}
		// 팩토리얼은 이렇게 표현할 수 있기 때문에 재귀호출을 하는 것임.
		// 5! = 5 * 4 * 3 * 2 * 1
		// 5! = 5 * 4!
		else {
//			System.out.println( "###### 재귀호출로 계산 num : " + num * factorial( num - 1 ) );
			return num * factorial( num - 1 );
		}
	}

}
