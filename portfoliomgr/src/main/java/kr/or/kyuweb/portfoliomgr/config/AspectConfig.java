package kr.or.kyuweb.portfoliomgr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages="kr.or.kyuweb.portfoliomgr.aop")
public class AspectConfig {

}
