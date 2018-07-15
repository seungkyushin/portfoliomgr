package kr.or.kyuweb.portfoliomgr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"kr.or.kyuweb.portfoliomgr.dao","kr.or.kyuweb.portfoliomgr.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
