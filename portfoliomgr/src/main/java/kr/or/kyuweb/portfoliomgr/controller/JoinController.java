package kr.or.kyuweb.portfoliomgr.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;
import kr.or.kyuweb.portfoliomgr.service.LogService;
import kr.or.kyuweb.portfoliomgr.service.VisiterService;

@Controller
public class JoinController {

	@Autowired
	VisiterService visiterService;
	
	@Autowired
	LogService logService;
	
	@GetMapping(path="/join")
	public String showpage() {
		return "join";
	}
	
	@PostMapping(path="/addvisiter")
	public String addVisiter( @RequestParam(name="name") String name,
			@RequestParam(name="email") String email,
			@RequestParam(name="password") String password,
			@RequestParam(name="organization") String organization,
			HttpServletRequest req){
		System.out.println("ViewPageController : /addVisiter");

		VisiterDto visiter = new VisiterDto();
		
		visiter.setName(name);
		visiter.setEmail(email);
		visiter.setPassword(password);
		
		if("".equals(organization) == true)
			organization = "없음";
		
		visiter.setOrganization(organization);
		
		String clientIp = logService.getClientIP(req);
		
		int result = visiterService.add(visiter,clientIp);
		
		if( result > 0) {
			//< 생성 성공
			req.setAttribute("Message", "성공적으로 가입되었습니다!");
			return "main";
		}else{
			if( result == -1)
				req.setAttribute("Message", "동일한 Email이 존재합니다.");
			else
				req.setAttribute("Message", "가입에 실패하였습니다.");
			
			return "join";
			
		}

	}
	
	 
}
