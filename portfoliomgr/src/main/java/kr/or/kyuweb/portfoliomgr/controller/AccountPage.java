package kr.or.kyuweb.portfoliomgr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;
import kr.or.kyuweb.portfoliomgr.service.VisiterService;

@Controller
public class AccountPage {

	@Autowired
	VisiterService visiterService;
	
	@GetMapping(path="/account")
	public String pagemove() {
		return "account";
	}
	
	@PostMapping(path="/addVisiter")
	public String addVisiter( @RequestParam(name="name") String name,
			@RequestParam(name="email") String email,
			@RequestParam(name="password") String password,
			@RequestParam(name="organization") String organization){
		System.out.println("ViewPageController : /addVisiter");
		
		VisiterDto visiter = new VisiterDto();
		
		visiter.setName(name);
		visiter.setEmail(email);
		visiter.setPassword(password);
		visiter.setOrganization(organization);
		
		System.out.println("data :" + visiter);
		
		visiterService.add(visiter);
		
		return "main";
	}
}
