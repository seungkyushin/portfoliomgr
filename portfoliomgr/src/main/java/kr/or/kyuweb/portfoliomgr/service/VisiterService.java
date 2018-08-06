package kr.or.kyuweb.portfoliomgr.service;

import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;
public interface VisiterService {

	public VisiterDto getVisiter(String email);
	public VisiterDto getVisiter(int id);
	public int add(VisiterDto data,String ip);
	public int delete(VisiterDto data, String ip);
	public int update(VisiterDto data, String ip);
	public VisiterDto checkLogin(String email, String password, String ip);
	public boolean checkPassword(String newPwd ,String originalPwd);
}
