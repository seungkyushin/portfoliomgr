package kr.or.kyuweb.portfoliomgr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kyuweb.portfoliomgr.dao.FileInfoDao;
import kr.or.kyuweb.portfoliomgr.dao.ProjectDao;
import kr.or.kyuweb.portfoliomgr.dto.FileInfoDto;
import kr.or.kyuweb.portfoliomgr.dto.ProjectDto;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	FileInfoDao fileInfoDao;
	
	@Override
	public List<Map<String, Object>> getProjectList() {
		
		List<ProjectDto> projectDto = projectDao.selectAll();
		List<Map<String, Object>> resultList = new ArrayList<>();
		
		for( ProjectDto data : projectDto) {
			Map<String, Object> pramMap = new HashMap<>();
			
			pramMap.put("id", data.getId());
			pramMap.put("action", data.getAction());
			pramMap.put("description", data.getDescription());
			pramMap.put("subdescription", data.getSubDescription());
			pramMap.put("name", data.getName());
			pramMap.put("url", data.getUrl());
			
			FileInfoDto fileInfoDto = fileInfoDao.selectById(data.getFileId());
			pramMap.put("image", fileInfoDto.getSavePath());
			
			resultList.add(pramMap);
		}
		
		return resultList;
	}

}
