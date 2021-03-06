package kr.or.kyuweb.portfoliomgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DescriptionController {

	@GetMapping(path="/description")
	public String showPage() {
		return "description";
	}
	
	@GetMapping(path="/descriptionProject")
	public String showDescriptionProjectPage(@RequestParam(name="id") int projectId,
			ModelMap modelMap) {
		modelMap.addAttribute("projectId", projectId);

		return "descriptionProject";
	}
	
	
	
	
}
