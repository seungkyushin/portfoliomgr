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
@Transactional(readOnly=false)
public class VisiterServiceImpl implements VisiterService{
	
	
	@Autowired
	VisiterDao visiterDao; 

	@Autowired
	LogService logService;
	
	@Autowired 
	DateFormat  dateFormat;
	
	@Override
	public void add(VisiterDto data ,String ip) throws SQLException, DuplicateKeyException {
		
		//< 패스워드 암호화 
		String encryption = Encryption.SHA512(data.getPassword());
		data.setPassword(encryption);
		data.setCreateDate(dateFormat.format(new Date()));
			
		logService.recordLog("test","방문자 가입 전 로그",data.getEmail(),ip);
		
		visiterDao.insert(data);
				
		logService.recordLog("info","방문자 가입 성공",data.getEmail(),ip);
	}

	@Override
	public int delete(VisiterDto data ,String ip) {
		return 0;
	}

	@Override
	public VisiterDto checkLogin(String email,String password ,String ip) {
		System.out.println("로그인 시도");
		logService.recordLog("info", "로그인 시도 ", email,ip);
		
		VisiterDto visiter = this.getVisiter(email);
		
		if(visiter != null && checkPassword(password, visiter.getPassword()) == true) {		
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//< 마지막 로그인 갱신
			visiterDao.updateLastLoginTime(visiter.getEmail(), dateFormat.format(new Date()));
			//< 로그
			logService.recordLog("info", "로그인 성공", visiter.getEmail(),ip);
			System.out.println("로그인 성공");
			return visiter;
			
		}
		System.out.println("로그인 실패");
		
		return null;
	}

	@Override
	public VisiterDto getVisiter(String email) {
		
		if( email != null || email.isEmpty() ) {
			if(visiterDao != null )
				return visiterDao.selectByEmail(email);
		}
		
		return null;
	}

	@Override
	public VisiterDto getVisiter(int id) {
		if( id != 0 ) {
			if(visiterDao != null )
				return visiterDao.selectById(id);
		}
		
		return null;
	}

	@Override
	public int update(VisiterDto data, String ip) {
		
		if( data == null || ip == null || ip.isEmpty() ) {
			return FAILED;
		}

		//< 로그
		//logService.recordLog("update", "정보 갱신 성공", email,ip);
		
		try {
			
			 visiterDao.updateInfo(data.getEmail(),
					 Encryption.SHA512(data.getPassword()),
					 data.getOrganization());
			
			 return SUCCESS;
		} catch (SQLException e) {
			 logService.recordLog("error",e.getSQLState() + ":" + e.getMessage(),data.getEmail(),ip);
			 return FAILED;
		}
	}

	@Override
	public boolean checkPassword(String newPwd, String originalPwd) {
	
		return originalPwd.equals(Encryption.SHA512(newPwd));
	}

}
