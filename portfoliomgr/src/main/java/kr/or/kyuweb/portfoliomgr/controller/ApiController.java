package kr.or.kyuweb.portfoliomgr.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kyuweb.portfoliomgr.service.ProjectService;

@RestController
@RequestMapping(path="/api")
public class ApiController {

	
	@Autowired
	ProjectService projectService;
	
	@PostMapping(path="/project")
	public Map<String,Object> getProjectList()  {
		Map<String,Object> resultDataMap = new HashMap<>();
		resultDataMap.put("projectList", projectService.getProjectList());
		return resultDataMap;
	}
	
}
