package api;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 회원 날짜 검색 객체
 * @author cheeeeze
 *
 */
public class SearchCommand {

	@DateTimeFormat( pattern = "yyyyMMddHH" )
	private LocalDateTime from;
	
	@DateTimeFormat( pattern = "yyyyMMddHH" )
	private LocalDateTime to;
	
	public LocalDateTime getFrom() {
		return from;
	}
	
	public LocalDateTime getTo() {
		return to;
	}
	
	public void setTo( LocalDateTime to ) {
		this.to = to;
	}
	
	public void setFrom( LocalDateTime from ) {
		this.from = from;
	}
}
