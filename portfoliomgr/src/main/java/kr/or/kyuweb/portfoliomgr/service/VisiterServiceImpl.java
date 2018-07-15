package kr.or.kyuweb.portfoliomgr.service;

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
	VisiterDao visiterDao; 

	@Autowired
	LogDao logDao; 
	
	@Override
	@Transactional(readOnly=false)
	public int add(VisiterDto data) {
		
		int result = visiterDao.insert(data);
		
		LogDto log = new LogDto();
		log.setType("database");
		log.setDescription("방문자 가입 완료 : " + data.getEmail());
		log.setCreateDate(new Date());
		
		logDao.insert(log);
		
		return result;
	}

	@Override
	public int delete(VisiterDto data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
