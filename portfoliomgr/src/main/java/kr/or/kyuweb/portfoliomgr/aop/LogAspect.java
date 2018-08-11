package kr.or.kyuweb.portfoliomgr.aop;


import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.or.kyuweb.portfoliomgr.service.LogService;



@Component
@Aspect
public class LogAspect {
	
	@Autowired
	LogService logService;
	
	@Around("execution(* checkLogin(..))")
	public Object logPrint(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println(pjp.getSignature().getName() + " around" );

	
		  for (Object obj : pjp.getArgs()) {
			  System.out.println(obj.toString());
	            if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) {
	                HttpServletRequest request = (HttpServletRequest) obj;

	        		String clientIp = (String)request.getAttribute("clientIp");
	        		System.out.println(clientIp);
	            }
	        }

		
		 return pjp.proceed();
	}
	
	@After(value = "execution(* checkLogin(String,String,String))")
	public void after(JoinPoint jp){
		System.out.println( jp.getSignature().getName() + "After");
	}
	@Before("execution(* checkLogin(String,String,String))")
	public void before(JoinPoint jp){
		System.out.println(jp.getSignature().getName() + "before");
	}
	@AfterReturning("execution(* checkLogin(String,String,String))")
	public void afterReturning(JoinPoint jp){
	
		System.out.println(jp.getSignature().getName() + " afterReturning");
	}
}

