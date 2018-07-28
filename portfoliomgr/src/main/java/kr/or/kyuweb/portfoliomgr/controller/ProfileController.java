package kr.or.kyuweb.portfoliomgr.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.kyuweb.portfoliomgr.dao.VisiterDao;
import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;
import kr.or.kyuweb.portfoliomgr.service.VisiterService;

@Controller
public class ProfileController {

	
	@Autowired
	VisiterService visiterService;
	
	@Autowired
	VisiterDao visiterDao;
	
	@GetMapping(path="/checkProfile")
	public String checkProfile(HttpSession hSession, ModelMap modelMap) {
		if( hSession != null ) {
			String email = (String)hSession.getAttribute("email");
			
			if( email == null || "".equals(email) ) { 
				modelMap.addAttribute("url","./login");
				modelMap.addAttribute("ResultMessage","로그인 후 이용해주세요.");
			}
		}
		
		return "checkProfile";
	}
	
	@PostMapping(path="/profile")
	public String showpage(@RequestParam(name="password") String password,
			HttpSession hSession,
			RedirectAttributes redirectAttr,
			ModelMap modelMap) {
		
		String email = (String)hSession.getAttribute("email");
		VisiterDto visiter = visiterService.getVisiter(email);
		
		if( visiter.getPassword().equals(password) == true ) {
			visiter.setPassword("");
			modelMap.addAttribute("visiter", visiter);
			return "profile";
			
		}else {
			
			redirectAttr.addFlashAttribute("errorMessage", "비밀번를 다시한번 확인하세요");
			return "redirect:checkProfile";
			
		}
		
	}
	
	@PostMapping(path="/modifyProfile")
	public String modifyProfile(@ModelAttribute VisiterDto visiter,
			HttpServletRequest req,
			RedirectAttributes redirectAttr,
			ModelMap modelMap) {
		
		
		
		try {
				visiterService.update(visiter, (String)req.getAttribute("clientIp"));
				visiter = visiterService.getVisiter(visiter.getEmail());
				
				modelMap.addAttribute("visiter", visiter);	
		}catch( DataAccessException e) {
			
				modelMap.addAttribute("ResultMessage", "업데이트를 실패했습니다. 다시 시도해주세요");		
		}
		return "profile";
		
	}
	
	
}
