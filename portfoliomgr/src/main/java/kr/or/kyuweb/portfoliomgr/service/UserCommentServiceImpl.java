package kr.or.kyuweb.portfoliomgr.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.kyuweb.portfoliomgr.dao.UserCommentDao;
import kr.or.kyuweb.portfoliomgr.dto.UserCommentDto;
import kr.or.kyuweb.portfoliomgr.dto.VisiterDto;

@Service
public class UserCommentServiceImpl implements UserCommentService{

	@Autowired
	UserCommentDao userCommentDao;
	
	@Autowired
	LogService logService;
	
	@Autowired
	VisiterService visiterService;
	
	@Autowired 
	DateFormat  dateFormat;
	
	@Override
	public List<UserCommentDto> getUserCommentByProjectId(int id, int start) {
		return userCommentDao.selectByProjectId(id,start);
	}

	@Override
	public List<UserCommentDto> getUserCommentByVisiterId(int id, int start) {
		return userCommentDao.selectByVisiterId(id,start);
	}

	@Override
	public int addUserComment(UserCommentDto data, String email, String ip) {
				
		VisiterDto visiter = visiterService.getVisiter(email);
		data.setVisiterId(visiter.getId());
		

		if(data.getShowCheck() == null ){
			data.setShowCheck("off");
		}

		data.setCreateDate(dateFormat.format(new Date()));
		
		return userCommentDao.insert(data);
	}

	@Override
	public int getUserCommentCount(int projectId) {
		return userCommentDao.selectCountByPorjectId(projectId);
	}

	@Override
	public List<UserCommentDto> getAllUserCommentByProjectId(int id) {
		return userCommentDao.selectAllByProjectId(id);
	}

	@Override
	public float getUserCommentAvgScore(int projectId) {
		List<UserCommentDto> UserCommentList =  getAllUserCommentByProjectId(projectId);
		
		float sumScore = 0;
		
		for( UserCommentDto data : UserCommentList)
		{
			sumScore += data.getScore();
		}
		
		sumScore /=  UserCommentList.size();
		
		return sumScore;
	}

}
