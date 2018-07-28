package kr.or.kyuweb.portfoliomgr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	
	
	@GetMapping("/comment")
	public String showPage(@RequestParam(name="projectId") int projectId,
			HttpServletRequest req) {
		
		ProjectDto projectDto = projectService.getProjectDto(projectId);
		
		req.setAttribute("name", projectDto.getName());
		req.setAttribute("projectId", projectDto.getId());
		return "comment";
	}
					
	@PostMapping("/addcomment")
	public String addComment(@ModelAttribute UserCommentDto data,
			HttpServletRequest req,
			HttpSession sec,
			ModelMap modelMap) {
		String clientIp = (String)req.getAttribute("clientIp");
		String email = (String)sec.getAttribute("email");
		
		System.out.println(data);
		System.out.println(email);
		
		int reuslt = userCommentService.addUserComment(data,email,clientIp);

		if( reuslt != 0 ) {
			modelMap.addAttribute("url","./descriptionProject?id="+data.getProjectId());
			modelMap.addAttribute("ResultMessage","덧글 남겨주셔서 감사합니다. 이전 페이지로 돌아갑니다.");
		}
		
		return "comment";
	}
	

	
	
	
}
	