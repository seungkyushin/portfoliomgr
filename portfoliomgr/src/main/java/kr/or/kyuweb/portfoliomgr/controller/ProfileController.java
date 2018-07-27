package kr.or.kyuweb.portfoliomgr.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;
import kr.or.kyuweb.portfoliomgr.service.VisiterService;

@Controller
public class ProfileController {

	
	@Autowired
	VisiterService visiterService;
	
	
	@GetMapping(path="/checkProfile")
	public String showpage(HttpSession hSession, ModelMap modelMap) {
		

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
			
			
			Map<String,Object> pramMap = new HashMap<String,Object>();
			
			pramMap.put("name", visiter.getName());
			pramMap.put("email", visiter.getEmail());
			pramMap.put("organization", visiter.getOrganization());
			pramMap.put("password","");
			
			modelMap.addAttribute("visiter", pramMap);
			
			return "profile";
			
		}else {
			
			redirectAttr.addFlashAttribute("errorMessage", "비밀번를 다시한번 확인하세요");

			return "redirect:checkProfile";
			
		}
		
	}
	
}
