package kr.or.kyuweb.portfoliomgr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.kyuweb.portfoliomgr.dto.ProjectDto;
import kr.or.kyuweb.portfoliomgr.dto.UserCommentDto;
import kr.or.kyuweb.portfoliomgr.service.LogService;
import kr.or.kyuweb.portfoliomgr.service.ProjectService;
import kr.or.kyuweb.portfoliomgr.service.UserCommentService;

@Controller
public class UserCommentController {

	@Autowired
	UserCommentService userCommentService;
	
	@Autowired
	ProjectService projectService;
		
	@Autowired
	LogService logService;
	
	@GetMapping("/comment")
	public String showpage(@RequestParam(name="projectId") int projectId,
			HttpServletRequest req) {
		
		ProjectDto projectDto = projectService.getProjectDto(projectId);
		
		req.setAttribute("name", projectDto.getName());
		req.setAttribute("projectId", projectDto.getId());
		return "comment";
	}
	
	@PostMapping("/addcomment")
	public String addComment(@ModelAttribute UserCommentDto data,
			@RequestParam(name="visiter") String email,
			HttpServletRequest req) {
		
		String clientIp = logService.getClientIP(req);
	
		System.out.println(data);
		userCommentService.addUserComment(data,email,clientIp);
		
		return "comment";
	}
	

	
	
	
}
	