package kr.or.kyuweb.portfoliomgr.service;

import java.util.List;
import java.util.Map;

import kr.or.kyuweb.portfoliomgr.dto.ProjectDto;

public interface ProjectService {
	
	public List<Map<String,Object>> getProjectListAll();
	public Map<String,Object> getProjectList(int id);

}
