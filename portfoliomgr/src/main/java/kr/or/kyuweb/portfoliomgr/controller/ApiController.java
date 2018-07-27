package kr.or.kyuweb.portfoliomgr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.kyuweb.portfoliomgr.dto.UserCommentDto;
import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;
import kr.or.kyuweb.portfoliomgr.service.ProjectService;
import kr.or.kyuweb.portfoliomgr.service.UserCommentService;
import kr.or.kyuweb.portfoliomgr.service.VisiterService;

@RestController
@RequestMapping(path="/api")
public class ApiController {

	
	@Autowired
	ProjectService projectService;

	@Autowired
	VisiterService visiterService;
			
	@Autowired
	UserCommentService userCommentService;
	
	@GetMapping(path="/project")
	public Map<String, Object> getProjectList(@RequestParam(name="id") int id){		
		Map<String, Object> resultMap = new HashMap<>();
		
		if( id == 0 )
			resultMap.put("projectList", projectService.getProjectListAll());
		else
			resultMap.put("projectList", projectService.getProjectList(id));
		
		return resultMap;
	}
	
	@GetMapping(path="/comment")
	public Map<String, Object> getCommentList(@RequestParam(name="projectId") int projectId,
			@RequestParam(name="start") int start
			){		
		Map<String, Object> resultMap = new HashMap<>();
		List<Object> paramList = new ArrayList<>();
		
		List<UserCommentDto> userCommentDto = userCommentService.getUserCommentByProjectId(projectId,start);
		
		for( UserCommentDto data : userCommentDto) {
			VisiterDto visiter = visiterService.getVisiter(data.getVisiterId());
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("visiterEmail", visiter.getEmail());
			paramMap.put("content", data.getContent());
			paramMap.put("type", data.getType());
			paramMap.put("score", data.getScore());
			paramMap.put("date", data.getCreateDate());
			
			paramList.add(paramMap);
		}
		
		
		resultMap.put("comments", paramList);
		resultMap.put("allCount",userCommentService.getUserCommentCount(projectId) );
		resultMap.put("avgScore",userCommentService.getUserCommentAvgScore(projectId) );
		resultMap.put("currentPage",start );
		
		
		return resultMap;
	}
	
	@GetMapping(path="/profile")
	public Map<String, Object> getProfileList(@RequestParam(name="projectId") int projectId,
			@RequestParam(name="start") int start
			){		
		Map<String, Object> resultMap = new HashMap<>();
		List<Object> paramList = new ArrayList<>();
		
		List<UserCommentDto> userCommentDto = userCommentService.getUserCommentByProjectId(projectId,start);
		
		for( UserCommentDto data : userCommentDto) {
			VisiterDto visiter = visiterService.getVisiter(data.getVisiterId());
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("visiterEmail", visiter.getEmail());
			paramMap.put("content", data.getContent());
			paramMap.put("type", data.getType());
			paramMap.put("score", data.getScore());
			paramMap.put("date", data.getCreateDate());
			
			paramList.add(paramMap);
		}
		
		
		resultMap.put("comments", paramList);
		resultMap.put("allCount",userCommentService.getUserCommentCount(projectId) );
		resultMap.put("avgScore",userCommentService.getUserCommentAvgScore(projectId) );
		resultMap.put("currentPage",start );
		
		
		return resultMap;
	}
	
}
