package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean {
	
	private String host;
	
	public void setHost( String host ) {
		this.host = host;
	}
	
	public void send() {
		System.out.println( "################" );
		System.out.println( "Client.send() to " + host );
	}

	@Override
	public void destroy() throws Exception {
		System.out.println( "##############################");
		System.out.println( "Bean 객체가 소멸됩니다.........");
		System.out.println( "##############################");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println( "##############################");
		System.out.println( "Bean 객체의 초기화가 이루어집니다......");
		System.out.println( "##############################");
		
	}

	
}
