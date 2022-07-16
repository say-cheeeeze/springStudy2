package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


/**
 * @author cheeeeze
 *
 */
public class MemberDAO {

	private JdbcTemplate jdbcTemplate;
	
	public MemberDAO( DataSource dataSource ) {
		this.jdbcTemplate = new JdbcTemplate( dataSource );
	}
	
	/**
	 * email 일치하는 회원 정보를 가져옵니다.
	 * @param email
	 * @return
	 */
	public Member selectByEmail( String email ) {
		
		String query = "select * from MEMBER WHERE EMAIL = ?"; 
		
		List<Member> results = jdbcTemplate.query( 
				query, 
				new MemberRowMapper(), email ); 
		return results.isEmpty() ? null : results.get( 0 );
	}
	
	/**
	 * 새 Member 추가
	 * @param member
	 */
	public void insert( Member member ) {
		
		// mysql 의 auto_increment 자동생성된 key 값을 구해주는 keyHolder 구현 클래스이다.
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String query = "insert into MEMBER ( EMAIL, PASSWORD, NAME, REGDATE ) values ( ?,?,?,? )";

		// insert update delete 쿼리는 update() 메소드를 사용한다.
		jdbcTemplate.update( new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				
				PreparedStatement pstmt = con.prepareStatement( query, new String[] {"ID"} );
				
				pstmt.setString( 1, member.getMemberEmail() );
				pstmt.setString( 2, member.getMemberPassword() );
				pstmt.setString( 3, member.getMemberName() );
				pstmt.setTimestamp( 4, Timestamp.valueOf( member.getInputDateTime() ) );
				
				// 생성된 Preparedstatement 반환
				return pstmt;
			}
		}, keyHolder );
		
		Number keyValue = keyHolder.getKey();
		member.setMemberId( keyValue.longValue() );
		
	}
	
	/**
	 * Member 수정
	 * @param member
	 */
	public void update( Member member ) {
		
		String query = "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?";
		
		jdbcTemplate.update( query, member.getMemberName(), member.getMemberPassword(), member.getMemberEmail() );
		
	}
	
	/**
	 * 회원 목록을 가져옵니다.
	 * @return
	 */
	public List<Member> listAll() {
		
		String query = "select * from MEMBER";
		
		List<Member> results = jdbcTemplate.query(
				query,
				new MemberRowMapper());
		return results;
	} 
	
	/**
	 * 회원 총 수를 가져옵니다.
	 * queryForObject 는 두번째로 선언된 파라미터 타입으로 반환값을 반환한다.
	 * queryForObject() 메서드를 사용하려면 쿼리 실행 결과는 반드시 한 행이어야 한다. 0개 안되고 2개 안됨
	 * @return
	 */
	public int memberCount() {
		
		String query = "select count(*) FROM MEMBER";
		
		Integer count = jdbcTemplate.queryForObject(
				query, Integer.class );
		
		return count;
	}
	
	/**
	 * RowMapper 구현을 여러곳에서 사용하기 위해
	 * @author cheeeeze
	 */
	public class MemberRowMapper implements RowMapper<Member> {

		@Override
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member member = new Member(
					rs.getString( "EMAIL" ),
					rs.getString( "PASSWORD" ),
					rs.getString( "NAME" ),
					rs.getTimestamp( "REGDATE" ).toLocalDateTime() );
			member.setMemberId( rs.getLong( "ID" ) );
			return member;
		}
		
	}
	
}
