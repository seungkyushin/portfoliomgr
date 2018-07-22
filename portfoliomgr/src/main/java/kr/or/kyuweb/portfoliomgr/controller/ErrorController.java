package kr.or.kyuweb.portfoliomgr.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/WEB-INF/views/error")
public class ErrorController {

	@RequestMapping(value="/notfound")
	public String pageError400( HttpServletResponse res,
			Model model) {
		
		 res.setStatus(HttpServletResponse.SC_OK);
		 
		 model.addAttribute("head", "아이고 이런!");
		 model.addAttribute("content", "개발자가 싫어하는 경로로 접근하셨네요,, 돌아세요~");
		
		return "notfound";
	
	}
}
