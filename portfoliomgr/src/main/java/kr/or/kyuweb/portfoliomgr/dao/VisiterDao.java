package kr.or.kyuweb.portfoliomgr.dao;

import static kr.or.kyuweb.portfoliomgr.sql.VisiterSql.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;

@Repository
public class VisiterDao {
	
	
	 NamedParameterJdbcTemplate jdbc;
	 SimpleJdbcInsert insertAction;
	 RowMapper<VisiterDto> rowMapper = new BeanPropertyRowMapper<>(VisiterDto.class);
	 
	 

	 public VisiterDao(DataSource dataSource) {
		 jdbc = new NamedParameterJdbcTemplate(dataSource);
		 insertAction = new SimpleJdbcInsert(dataSource)
				 .withTableName("visiter")
				 .usingGeneratedKeyColumns("id");
		 
	 }
	 
	 public List<VisiterDto> selectAll() {
		  return jdbc.query(SELECT_ALL,rowMapper);
	 }
	 
	 public int insert(VisiterDto data) {
		 
		 SqlParameterSource params = new BeanPropertySqlParameterSource(data);
			return insertAction.executeAndReturnKey(params).intValue();
	 }
	 
	 public int delete(String email) {
		 
		 Map<String,String> paramMap = new HashMap<>();
		 paramMap.put("email", email);
		return 0;
	 }
	

}
