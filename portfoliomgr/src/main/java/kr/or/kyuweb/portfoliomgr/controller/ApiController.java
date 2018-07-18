package kr.or.kyuweb.portfoliomgr.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kyuweb.portfoliomgr.service.ProjectService;

@RestController
@RequestMapping(path="/api")
public class ApiController {

	
	@Autowired
	ProjectService projectService;
	
	@GetMapping(path="/project")
	public Map<String,Object> getProjectList(@RequestParam(name="id") int id)  {
		System.out.println(id);
		Map<String,Object> resultDataMap = new HashMap<>();
		
		//if( id == 0 )
			//resultDataMap.put("projectList", projectService.getProjectList());
		//else
			//resultDataMap.put("projectList", projectService.getProjectList());
		
		return resultDataMap;
	}
	
}
