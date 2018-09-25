package com.bee.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

//@EnableTransactionManagement   Springboot 2.0默认开启事务的
@SpringBootApplication
@EnableAsync  //表示开启异步处理，Springboot 会在启动时扫包，有@async，即aop技术会帮你开一个独立线程去处理
public class AutoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AutoApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(AutoApplication.class, args);
	}

//	@Bean
//	public FilterRegistrationBean filterRegistrationBean() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setForceEncoding(true);
//		characterEncodingFilter.setEncoding("UTF-8");
//		registrationBean.setFilter(characterEncodingFilter);
//		return registrationBean;
//	}

}
