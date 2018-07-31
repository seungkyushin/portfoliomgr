package kr.or.kyuweb.portfoliomgr.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public String checkProfile(	) {
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
			HttpServletResponse res,
			RedirectAttributes redirectAttr,
			ModelMap modelMap) {

		
		try {
				visiterService.update(visiter, (String)req.getAttribute("clientIp"));
				visiter = visiterService.getVisiter(visiter.getEmail());
				
				modelMap.addAttribute("visiter", visiter);
				req.setAttribute("resultMsg", "수정되었습니다.");
		}catch( DataAccessException e) {
			req.setAttribute("resultMsg", "수정에 실패했습니다.");
			return "profile";
		}

		return "main";
		
	}
	
	
}
