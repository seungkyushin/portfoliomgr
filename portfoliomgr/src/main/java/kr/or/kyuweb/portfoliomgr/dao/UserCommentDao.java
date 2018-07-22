package kr.or.kyuweb.portfoliomgr.dao;

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

import kr.or.kyuweb.portfoliomgr.dto.UserCommentDto;

@Repository
public class UserCommentDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private static final int LILIT = 4;
	private RowMapper<UserCommentDto> rowMapper = new BeanPropertyRowMapper<>(UserCommentDto.class);
	
	public UserCommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("user_comment")
				.usingGeneratedKeyColumns("id");
		}
	
	public List<UserCommentDto> selectByProjectId(int id,int start){
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("start", start);
		paramMap.put("limit", LILIT);
		return jdbc.query("SELECT * FROM user_comment WHERE project_id=:id AND show_check='off' limit :start, :limit", paramMap, rowMapper);
	}
	
	public List<UserCommentDto> selectAllByProjectId(int id){
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("id", id);
		return jdbc.query("SELECT * FROM user_comment WHERE project_id=:id", paramMap, rowMapper);
	}
	
	
	public List<UserCommentDto> selectByVisiterId(int id,int start){
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("start", start);
		paramMap.put("limit", LILIT);
		return jdbc.query("SELECT * FROM user_comment WHERE visiter_id=:id AND show_check='off' limit :start, :limit", paramMap, rowMapper);
	}
	
	public int selectCountByPorjectId(int ProjectId){
		Map<String,Integer> paramMap = new HashMap<>();
		paramMap.put("ProjectId", ProjectId);
		return jdbc.queryForObject("SELECT COUNT(*) FROM user_comment WHERE project_id=:ProjectId", paramMap, Integer.class);
	}
	
	public int insert(UserCommentDto data) {
		SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(data);
		return insertAction.executeAndReturnKey(sqlParam).intValue();
	};
}

