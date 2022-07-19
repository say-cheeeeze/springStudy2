package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * spring message 를 사용하기 위해 MessageSource 타입의 bean 을 추가한다.
 * 주의 : 빈 이름을 messageSource 로 지정해야한다. 다른 이름으로 사용하면 정상적으로 작동하지 않는다.
 * 
 * @author cheeeeze
 *
 */
@Configuration
public class MessageBeanConfig {
	
	@Bean
	public MessageSource messageSource() {
		
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		
		// message 패키지에 속한 label 프로퍼티 파일로부터 메시지를 읽어온다고 설정.
		// src/main/resources 디렉토리도 클래스 path 에 추가되고, message 폴더도 message 패키지에 해당된다.
		// 따라서 이 설정은 src/main/resources/label.properties 파일을 읽어온다.
		messageSource.setBasenames( "message.label" );
		
		messageSource.setDefaultEncoding( "UTF-8" );
		return messageSource;
	}

}
