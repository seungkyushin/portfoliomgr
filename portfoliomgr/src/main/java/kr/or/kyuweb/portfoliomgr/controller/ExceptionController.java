package kr.or.kyuweb.portfoliomgr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("kr.or.kyuweb.portfoliomgr")
public class ExceptionController {

	
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView runtimeExecption(RuntimeException re, 
			HttpServletRequest req, 
			ModelAndView view) {
		
		view.setViewName("views/error/notfound");
		view.addObject("message", re.getMessage());
		String referer = req.getHeader("Referer");
		view.addObject("from", referer );
		view.addObject("content", "허허");
		return view;
	}
	
}
