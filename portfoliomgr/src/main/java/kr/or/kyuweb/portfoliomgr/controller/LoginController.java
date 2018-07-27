package kr.or.kyuweb.portfoliomgr.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class LoginController {
	
	@Autowired
	VisiterService visiterService;
	
	
	@GetMapping(path="/login")
	public String showpage() {
		
		return "login";
	}
	
	@GetMapping(path="/logout")
	public String logout(HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap modelMap) {
		
		session.invalidate();
		Cookie[] cookies = req.getCookies();
		for(int i = 0 ; i<cookies.length; i++){            
			cookies[i].setMaxAge(0);                        
			res.addCookie(cookies[i]);     
			}
		
		modelMap.addAttribute("ResultMessage", "성공적으로 로그아웃 되셨습니다!");

		return "main";
	}
	
	
	@PostMapping(path="/logincheck")
	public String loginCheck(@RequestParam(name="email") String email ,
			@RequestParam(name="password") String password,
			HttpSession session,
			RedirectAttributes redirectAttr,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap modelMap) {
	

		String clientIp = getClientIP(req);
		
		VisiterDto visiter = visiterService.checkLogin(email, password, clientIp);
		if( visiter != null ) {
			
			session.setAttribute("email", email);
			session.setMaxInactiveInterval(10*60); // 10분 유지

			 
			Cookie info = new Cookie("email", email);    // 쿠키를 생성한다. 이름:testCookie, 값 : Hello Cookie
			
			info.setMaxAge(60*60*10);                                 // 쿠키의 유효기간을 365일로 설정한다.
			info.setPath("/");
			res.addCookie(info);   
			
			modelMap.addAttribute("ResultMessage","'" + visiter.getName() +"'님 반갑습니다!");
			
			return "main";

		}else {
			
			redirectAttr.addFlashAttribute("errorMessage", "이메일이 없거나 비밀번호가 잘못 됬습니다.");

			return "redirect:login";
			
		}
		
	}
	
	
	 public String getClientIP(HttpServletRequest request) {

	     String ip = request.getHeader("X-FORWARDED-FOR"); 
	     
	     if (ip == null || ip.length() == 0) {
	         ip = request.getHeader("Proxy-Client-IP");
	     }

	     if (ip == null || ip.length() == 0) {
	         ip = request.getHeader("WL-Proxy-Client-IP");  // 웹로직
	     }

	     if (ip == null || ip.length() == 0) {
	         ip = request.getRemoteAddr() ;
	     }
	     
	     return ip;

	 }

	
	

}
