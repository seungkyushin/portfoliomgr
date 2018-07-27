package kr.or.kyuweb.portfoliomgr.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;

@Controller
public class DescriptionController {

	@GetMapping(path="/description")
	public String showpage() {
		return "description";
	}
	

	@GetMapping(path="/descriptionProject")
	public String showDescriptionProjectPage(@RequestParam(name="id") int projectId,
			HttpServletRequest req,
			ModelMap modelMap,
			HttpSession sec) {
		modelMap.addAttribute("projectId", projectId);

		
		if( sec != null ) {
			String email = (String)sec.getAttribute("email");
			
			if( email == null || "".equals(email) ) { 
				modelMap.addAttribute("ResultMessage","로그인을 하셔야 원활한 이용이 가능합니다.");
			}
		}
		return "descriptionProject";
	}
	
	
	
	
}
