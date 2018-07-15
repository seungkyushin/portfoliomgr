package kr.or.kyuweb.portfoliomgr.sql;

public class VisiterSql {
	
	public static final String TABLE_NAME = "visiter";
	public static final String SELECT_ALL = "SELECT id,name,email,organization,last_login FROM" + TABLE_NAME;
	public static final String DELETE_BY_EMAIL = "DELETE FROM" + TABLE_NAME + "WHERE email=:email";

}
