package kr.or.kyuweb.portfoliomgr.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.kyuweb.portfoliomgr.dto.FileInfoDto;

@Repository
public class FileInfoDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<FileInfoDto> rowMapper = new BeanPropertyRowMapper<>(FileInfoDto.class);
	
	public FileInfoDao(DataSource dataSource) {
		 this.jdbc = new NamedParameterJdbcTemplate(dataSource);

	}
	
	public  FileInfoDto selectById(int id) {
			Map<String,Integer> paramMap = new HashMap<>();
			paramMap.put("id", id);
			return jdbc.queryForObject("SELECT * FROM file_info WHERE id=:id", paramMap, rowMapper);
		
	}
}
