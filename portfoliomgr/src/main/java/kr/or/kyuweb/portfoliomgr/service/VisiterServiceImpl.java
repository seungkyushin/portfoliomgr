package kr.or.kyuweb.portfoliomgr.service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.kyuweb.portfoliomgr.dao.VisiterDao;
import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;
import kr.or.kyuweb.portfoliomgr.util.Encryption;

@Service
public class VisiterServiceImpl implements VisiterService{
	
	@Autowired
	VisiterDao visiterDao; 

	@Autowired
	LogService logService;
	
	@Override
	@Transactional(readOnly=false)
	public int add(VisiterDto data ,String ip) {
		
		int result = 0;

		try {
			
				//< 패스워드 암호화 
				String encryption = Encryption.SHA512(data.getPassword());
				data.setPassword(encryption);
				
				DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				data.setCreateDate(dateFormat.format(new Date()));
								
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
		return 0;
	}

	@Override
	public VisiterDto checkLogin(String email,String password ,String ip) {
		
		logService.recordLog("info", "로그인 시도 ", email,ip);
		
		VisiterDto visiter = this.getVisiter(email);
		
		if(visiter != null && checkPassword(password, visiter.getPassword()) == true) {		
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//< 마지막 로그인 갱신
			visiterDao.updateLastLoginTime(visiter.getEmail(), dateFormat.format(new Date()));
			//< 로그
			logService.recordLog("info", "로그인 성공", visiter.getEmail(),ip);
			
			return visiter;
			
		}
		
		
		return null;
	}

	@Override
	public VisiterDto getVisiter(String email) {
		return visiterDao.selectByEmail(email);
	}

	@Override
	public VisiterDto getVisiter(int id) {
		return visiterDao.selectById(id);
	}

	@Override
	public int update(VisiterDto data, String ip) {
		String email = data.getEmail();
		String password = Encryption.SHA512(data.getPassword());
		String organization = data.getOrganization();

		//< 로그
		logService.recordLog("update", "정보 갱신 성공", email,ip);
		
		return visiterDao.updateInfo(email,password,organization);
	}

	@Override
	public boolean checkPassword(String newPwd, String originalPwd) {
		return originalPwd.equals(Encryption.SHA512(newPwd));
	}

}
