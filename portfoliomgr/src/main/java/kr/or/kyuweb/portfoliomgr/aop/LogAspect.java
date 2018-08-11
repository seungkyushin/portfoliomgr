package kr.or.kyuweb.portfoliomgr.aop;


import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class LogAspect {
	
	@Around("execution(* checkLogin(..))")
	public Object logPrint(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println(pjp.getSignature().getName() + " around" );
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

