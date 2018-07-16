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
	
	
	public void insertLog(String type, String Description) {
		
		LogDto log = new LogDto();
		log.setType(type);
		log.setDescription(Description);
		log.setCreateDate(dateFormat.format(new Date()));
		
		logDao.insert(log);
		
	}
	
	@Override
	
	public int add(VisiterDto data) {
		
		DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		data.setCreateDate(dateFormat.format(new Date()));
		
		int result = visiterDao.insert(data);

		insertLog("info","새로 가입 하였습니다. " + data.getEmail());
		
		return result;
	}

	@Override
	public int delete(VisiterDto data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkLogin(String email,String password) {
		// TODO Auto-generated method stub
		
		
		VisiterDto visiter = visiterDao.selectByEmail(email);
		
		if(visiter.getPassword().equals(password) == true) {		

			//< 마지막 로그인 갱신
			visiterDao.updateLastLoginTime(visiter.getEmail(), dateFormat.format(new Date()));
			//< 로그
			insertLog("info", visiter.getEmail() + "님이 로그인 하셨습니다.");
			
			return true;
			
		}
		
		return false;
	}

}
