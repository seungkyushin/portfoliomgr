package kr.or.kyuweb.portfoliomgr.service;

import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;
public interface VisiterService {

	public static final int SUCCESS = 1;
	public static final int FAILED = -1;
	public static final int ERROR_DUPLICATE_FOR_EMAIL = -100;
	
	public VisiterDto getVisiter(String email);
	public VisiterDto getVisiter(int id);
	public int add(VisiterDto data,String ip);
	public int delete(VisiterDto data, String ip);
	public int update(VisiterDto data, String ip);
	public VisiterDto checkLogin(String email, String password, String ip);
	public boolean checkPassword(String newPwd ,String originalPwd);
}
