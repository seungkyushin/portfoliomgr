package kr.or.kyuweb.portfoliomgr.service;

import javax.servlet.http.HttpServletRequest;

public interface LogService {

	public String getClientIP(HttpServletRequest request);
	public int recordLog(String type, String description, String email , String ip);
}
