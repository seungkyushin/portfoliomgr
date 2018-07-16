package kr.or.kyuweb.portfoliomgr.dao;

import static kr.or.kyuweb.portfoliomgr.sql.VisiterSql.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
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
	
	
	 private NamedParameterJdbcTemplate jdbc;
	 private SimpleJdbcInsert insertAction;
	 private RowMapper<VisiterDto> rowMapper = new BeanPropertyRowMapper<>(VisiterDto.class);
	 
	 

	 public VisiterDao(DataSource dataSource) {
		 this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		 this.insertAction = new SimpleJdbcInsert(dataSource)
				 .withTableName("visiter")
				 .usingGeneratedKeyColumns("id");
		 
	 }
	 
	 public List<VisiterDto> selectAll() {
		  return jdbc.query(SELECT_ALL,rowMapper);
	 }
	 
	 public VisiterDto selectByEmail(String email){
		 Map<String,String> paramMap = new HashMap<>();
		 paramMap.put("email", email);
		 
		 
		 try {
			 	return jdbc.queryForObject(SELECT_BY_EMAIL, paramMap, rowMapper);
		 }catch(EmptyResultDataAccessException e)
		 {
			 return null;
		 }
		 
		 
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
	 
	 public int updateLastLoginTime(String email,String lastLoinDate) {
		 
		 Map<String,String> paramMap = new HashMap<>();
		 paramMap.put("email", email);
		 paramMap.put("lastLoginDate", lastLoinDate);
		 
		 return jdbc.update(UPDATE_LAST_LOGIN_TIME, paramMap);
	 }
	
	

}
