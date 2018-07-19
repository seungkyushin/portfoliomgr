package kr.or.kyuweb.portfoliomgr.service;

import org.springframework.stereotype.Service;

import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;


public interface VisiterService {

	public int add(VisiterDto data,String ip);
	public int delete(VisiterDto data, String ip);
	public boolean checkLogin(String email, String password, String ip);
	
}
