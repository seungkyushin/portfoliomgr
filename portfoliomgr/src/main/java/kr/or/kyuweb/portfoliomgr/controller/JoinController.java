package kr.or.kyuweb.portfoliomgr.controller;


import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;
import kr.or.kyuweb.portfoliomgr.service.VisiterService;

@Controller
public class JoinController {

	@Autowired
	VisiterService visiterService;
	
	
	@GetMapping(path="/join")
	public String showPage() {
		return "join";
	}
	
	@PostMapping(path="/addvisiter")
	public String addVisiter( @ModelAttribute VisiterDto visiter,
			HttpServletRequest req,
			ModelMap modelMap){

		String clientIp = (String)req.getAttribute("clientIp");
		
		if("".equals(visiter.getOrganization()) == true)
			visiter.setOrganization("없음");

		try {
			visiterService.add(visiter,clientIp);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			req.setAttribute("resultMsg", "동일한 Email이 존재합니다.");
			return "join";
		} catch (SQLException e) {
			e.printStackTrace();
			return "join";
		}
		
		
		req.setAttribute("resultMsg","성공적으로 가입되었습니다!");
		return "main";
		
	}
	
	 
}
