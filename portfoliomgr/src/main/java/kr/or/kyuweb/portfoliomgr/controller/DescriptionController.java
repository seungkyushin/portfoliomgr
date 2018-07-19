package kr.or.kyuweb.portfoliomgr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DescriptionController {

	@GetMapping(path="/description")
	public String showpage() {
		return "description";
	}
	

	@GetMapping(path="/descriptionProject")
	public String showDescriptionProjectPage(@RequestParam(name="id") int projectId,
			HttpServletRequest req) {
		System.out.println(projectId);
		req.setAttribute("id", projectId);
		return "descriptionProject";
	}
	
	
	
	
}
