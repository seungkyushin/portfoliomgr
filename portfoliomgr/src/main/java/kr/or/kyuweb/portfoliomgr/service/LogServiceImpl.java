package kr.or.kyuweb.portfoliomgr.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kyuweb.portfoliomgr.dao.LogDao;
import kr.or.kyuweb.portfoliomgr.dto.LogDto;

@Service
public class LogServiceImpl implements LogService{

	private  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Autowired
	LogDao logDao;

	@Override
	public int recordLog(String type, String description, String email , String ip) {
		
		LogDto log = new LogDto();
		log.setType(type);
		log.setDescription(description);
		log.setVisiterEmail(email);
		log.setClientIp(ip);
		log.setCreateDate(dateFormat.format(new Date()));
		
		return logDao.insert(log);
		
	}

	@Override
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
