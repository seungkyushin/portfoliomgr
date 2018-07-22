package kr.or.kyuweb.portfoliomgr.service;

import java.util.List;

import kr.or.kyuweb.portfoliomgr.dto.UserCommentDto;

public interface UserCommentService {

	public List<UserCommentDto> getUserCommentByProjectId(int id, int start);
	public List<UserCommentDto> getAllUserCommentByProjectId(int id);
	public List<UserCommentDto> getUserCommentByVisiterId(int id, int start);
	public int addUserComment(UserCommentDto data,String email, String ip);
	public int getUserCommentCount(int projectId );
	public float getUserCommentAvgScore(int projectId);
}
