package chap07;

/**
 * 실행 시간을 계산하기 좋은 방식
 * 프록시 객체를 이용하여 계산하기!
 * @author cheeeeze
 *
 */
public class ExeTimeCalculator implements Calculator{
	
	private Calculator delegate; // 대리인ㅋ
	
	public ExeTimeCalculator( Calculator delegate ) {
		this.delegate = delegate;
	}
	
	@Override
	public long factorial(long num) {
		
		long startTime = System.nanoTime();
		
		long factorialResult = delegate.factorial( num );
		
		long endTime = System.nanoTime();
		
		System.out.printf( "%s.factorial(%d) 실행시간 = %d\n", 
				delegate.getClass().getSimpleName(),
				num,
				( endTime - startTime ) );
		
		return factorialResult;
	}
}
