package kr.or.kyuweb.portfoliomgr.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.kyuweb.portfoliomgr.dao.LogDao;
import kr.or.kyuweb.portfoliomgr.dao.VisiterDao;
import kr.or.kyuweb.portfoliomgr.dto.LogDto;
import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;

@Service
public class VisiterServiceImpl implements VisiterService{
	
	@Autowired
	private VisiterDao visiterDao; 

	@Autowired
	private LogDao logDao; 
	
	
	private  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	public void insertLog(String type, String description, String email , String ip) {
		
		LogDto log = new LogDto();
		log.setType(type);
		log.setDescription(description);
		log.setVisiterEmail(email);
		log.setClientIp(ip);
		log.setCreateDate(dateFormat.format(new Date()));
		
		logDao.insert(log);
		
	}
	
	@Override
	
	public int add(VisiterDto data ,String ip) {
		
		DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		data.setCreateDate(dateFormat.format(new Date()));
		
		int result = visiterDao.insert(data);

		insertLog("info","방문자 가입 성공",data.getEmail(),ip);
		
		return result;
	}

	@Override
	public int delete(VisiterDto data ,String ip) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkLogin(String email,String password ,String ip) {
		// TODO Auto-generated method stub
		
		
		VisiterDto visiter = visiterDao.selectByEmail(email);
		
		if(visiter != null && visiter.getPassword().equals(password) == true) {		

			//< 마지막 로그인 갱신
			visiterDao.updateLastLoginTime(visiter.getEmail(), dateFormat.format(new Date()));
			//< 로그
			insertLog("info", "로그인 성공", visiter.getEmail(),ip);
			
			return true;
			
		}
		
		return false;
	}

}
