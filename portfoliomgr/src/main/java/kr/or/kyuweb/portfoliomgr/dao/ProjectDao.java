package kr.or.kyuweb.portfoliomgr.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.kyuweb.portfoliomgr.dto.ProjectDto;

@Repository
public class ProjectDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ProjectDto> rowMapper = new BeanPropertyRowMapper<>(ProjectDto.class); 

	public ProjectDao(DataSource dataSource) {
		 this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		 this.insertAction = new SimpleJdbcInsert(dataSource)
				 .withTableName("project")
				 .usingGeneratedKeyColumns("id");
		 
	 }
	 
	 public List<ProjectDto> selectAll() {
		  return jdbc.query("SELECT * FROM project",rowMapper);
	 }
	 
	 public ProjectDto selectById(int id){
		  Map<String,Integer> paramMap = new HashMap<>();
		  paramMap.put("id", id);
		  return jdbc.queryForObject("SELECT * FROM project WHERE id=:id",paramMap,rowMapper);
	 }
	 
	 
}

