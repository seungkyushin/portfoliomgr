package kr.or.kyuweb.portfoliomgr.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String serveltPath = request.getServletPath();
			
		//< 로그인이 되어 있지 않으면 모든 쿠키먼저 삭제 
		if( request.getSession().getAttribute("email") == null ) {
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				
				int mas = cookies.length;
				for(int i=0; i<mas; i++){
					System.out.println(cookies[i].getName() +":"+ cookies[i].getValue());
					
						
					if( cookies[i].getName().equals("email") ) {
						cookies[i].setMaxAge(0);
						cookies[i].setPath("/");
						response.addCookie(cookies[i]);
						break;
					}
				}
			}
		}
		
		
		//< 로그인이 필요한 서비스
		if( serveltPath.equals("/comment") || serveltPath.equals("/checkProfile") 
		|| serveltPath.equals("/profile")  ){

			if( request.getSession().getAttribute("email") == null ) {
				RequestDispatcher rd= request.getRequestDispatcher("/login");
				request.setAttribute("resultMsg", "로그인이 필요한 서비스 입니다.");
				rd.forward(request, response);
				return false;
			}
				
		}

		request.setAttribute("clientIp", getClientIP(request));
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if( request.getAttribute("resultMsg") != null ) {
			this.makeCookie("resultMsg",request,response);
		}
		
		if( request.getAttribute("url") != null) {
			this.makeCookie("url",request,response);
		}

	}
	public void makeCookie(String attributeName, 
			HttpServletRequest req, HttpServletResponse res) {
		String resultMsg = (String)req.getAttribute(attributeName);
		Cookie cookie = createCookieMsg(attributeName,resultMsg);
		
		if(cookie != null)
			res.addCookie(cookie);
	}
	
	public Cookie createCookieMsg(String attributeName,String msg) {
		try {
			Cookie resultMsg = null;
					
			if(msg.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
				resultMsg = new Cookie(attributeName, 
						URLEncoder.encode(msg,"utf-8"));
				} else {
					resultMsg = new Cookie(attributeName,msg);
				}

				resultMsg.setMaxAge(60*60*10);                                 // 쿠키의 유효기간
				resultMsg.setPath("/");
				return resultMsg;
				
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
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
