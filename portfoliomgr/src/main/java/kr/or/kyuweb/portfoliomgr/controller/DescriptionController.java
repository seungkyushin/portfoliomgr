package kr.or.kyuweb.portfoliomgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DescriptionController {

	@GetMapping(path="/description")
	public String showpage() {
		return "description";
	}
	

	@GetMapping(path="/descriptionTodo")
	public String showDescriptionTodoPage() {
		return "descriptionTodo";
	}
	
	
	
	
}
