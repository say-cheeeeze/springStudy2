package com.cheeeeze.yj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
		
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		public void setJdbcTemplate( JdbcTemplate jdbcTemplate ) {
				this.jdbcTemplate = jdbcTemplate;
		}
		@RequestMapping( "/memberList" )
		public List<String> memberList() {
				
				String query = "select email from member";
				
				return jdbcTemplate.queryForList( query, String.class );
				
		}
}
