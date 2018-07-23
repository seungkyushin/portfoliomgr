package kr.or.kyuweb.portfoliomgr.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.or.kyuweb.portfoliomgr.dao.VisiterDao;
import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;

@Service
public class VisiterServiceImpl implements VisiterService{
	
	@Autowired
	VisiterDao visiterDao; 

	@Autowired
	LogService logService;
	
	@Override
	
	public int add(VisiterDto data ,String ip) {
		
		DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		data.setCreateDate(dateFormat.format(new Date()));
		int result = 0;
		
		try {
				result = visiterDao.insert(data);
				logService.recordLog("info","방문자 가입 성공",data.getEmail(),ip);
				
		}catch(SQLException e){
			logService.recordLog("error",e.getSQLState() + ":" + e.getMessage(),data.getEmail(),ip);
		}catch(DuplicateKeyException e) {
			result = -1;
			logService.recordLog("error",e.getMessage(),data.getEmail(),ip);
		}

		return result;
	}

	@Override
	public int delete(VisiterDto data ,String ip) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkLogin(String email,String password ,String ip) {
		// TODO Auto-generated method stub
		
		logService.recordLog("info", "로그인 시도 ", email,ip);
		
		VisiterDto visiter = this.getVisiter(email);
		
		if(visiter != null && visiter.getPassword().equals(password) == true) {		
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//< 마지막 로그인 갱신
			visiterDao.updateLastLoginTime(visiter.getEmail(), dateFormat.format(new Date()));
			//< 로그
			logService.recordLog("info", "로그인 성공", visiter.getEmail(),ip);
			
			return visiter.getId();
			
		}
		
		
		return 0;
	}

	@Override
	public VisiterDto getVisiter(String email) {
		return visiterDao.selectByEmail(email);
	}

	@Override
	public VisiterDto getVisiter(int id) {
		return visiterDao.selectById(id);
	}

}
