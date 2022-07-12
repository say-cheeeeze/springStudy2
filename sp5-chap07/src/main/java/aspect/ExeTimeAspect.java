package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 실행 시간을 계산하는 Aspect 객체
 * 
 * Aspect 로 사용할 클래스에 @Aspect 애노테이션을 붙인다.
 * @author cheeeeze
 */
@Aspect
public class ExeTimeAspect {
	
	/**
	 * @Pointcut 애노테이션으로 공통 기능을 적용할 pointcut 을 정의한다.
	 * 스프링에서는 정규 표현식이나 AspectJ 의 문법을 이용하여 pointcut 을 정의한다.
	 * chap07 패키지 또는 그 하위 패키지에 속한 bean 객체의 public 메서드에 적용했다.
	 */
	@Pointcut( "execution(public * chap07..*(..))")
	private void publicTarget() {
		
	}
	
	/**
	 * Around 애노테이션은 Around Advice 를 설정한다.
	 * 공통기능을 구현한 메서드에 @Around애노테이션을 적용한다.
	 * publicTarget() 메서드에 정의한 pointcut 에 공통 기능을 적용하겠다는 뜻이다.
	 */
	@Around( "publicTarget()" )
	public Object measure( ProceedingJoinPoint joinPoint ) throws Throwable {
		
		System.out.println("######### Around Aspect measure 메서드 실행....." );
		
		// ProceedingJoinPoint 는 프록시 대상 객체의 메서드를 호출할 때 사용한다.
		// proceed() 메서드를 사용해서 실제 대상 객체의 메서드를 호출한다.
		// 이 메서드를 시랳ㅇ하면 대상 객체의 메서드가 실행된다. 
		// 이 코드 이전과 이후에 공통 기능을 위한 코드를 위치시키며 된다.
		
		long startTime = System.nanoTime();
		
		try {
			Object result = joinPoint.proceed();
			return result;
		}
		finally {
			
			// java 에서는 메서드 이름과 파라미터를 합쳐서 메서드 시그니처라고 한다.
			// 리턴타입이나 익셉션 타입은 시그니처에 포함되지 않는다.
			// joinPoint 객체를 통해 호출한 메서드의 시그니처, 대상 객체, 인자 목록을 구할 수 있다.
			// 프록시 객체의 실행 결과를 확인하기 위해 printf 
			
			// Signature 인터페이스는 호출되는 메서드 이름(getName()), 호출되는 메서드의 많은 정보(toLongString()), 함축정보(toShortString() ) 
			// 을 나타내는 메서드를 제공한다.
			
			long finishTime = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf( "%s.%s(%s) 실행시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),
					sig.getName(), 
					Arrays.toString(joinPoint.getArgs()),
					(finishTime - startTime));
			
//			System.out.println( "호출 메서드 이름 getName() : " + sig.getName() );
//			System.out.println( "호출 메서드 모든 정보 toLongString() : " + sig.toLongString() );
//			System.out.println( "호출 메서드 간단 정보 toShortString() : " + sig.toShortString() );
		}
		
	}

}
