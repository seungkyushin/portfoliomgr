package kr.or.kyuweb.portfoliomgr.dao;

import static kr.or.kyuweb.portfoliomgr.sql.VisiterSql.*;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.kyuweb.portfoliomgr.dto.LogDto;

@Repository
public class LogDao {
	

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<LogDto> rowMapper = new BeanPropertyRowMapper<>(LogDto.class);
	 
	 

	 public LogDao(DataSource dataSource) {
		 this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		 this.insertAction = new SimpleJdbcInsert(dataSource)
				 .withTableName("log")
				 .usingGeneratedKeyColumns("id");
		 
	 }
	 
	 public List<LogDto> selectAll() {
		  return jdbc.query("SELECT * FROM log",rowMapper);
	 }
	 
	 public int insert(LogDto data) {
		 
		 SqlParameterSource params = new BeanPropertySqlParameterSource(data);
			return insertAction.executeAndReturnKey(params).intValue();
	 }
	 
 

}
