package spring;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 회원 정보 처리하는 레파지토리, DB 없이 map 으로 처리.
 * @author cheeeeze
 *
 */
public class MemberDAO {

	// memberId 로 활용
	private static long nextId = 0;
	
	private Map<String, Member> map = new HashMap<>();
	
	public Member selectByEmail( String email ) {
		return map.get( email );
	}
	
	public void insert( Member member ) {
		member.setMemberId( ++nextId );
		map.put( member.getMemberEmail(), member );
	}
	
	public void update( Member member ) {
		map.put( member.getMemberEmail(), member );
	}
	
	public Collection<Member> getMemberAll() {
		return map.values();
	}
	
}
