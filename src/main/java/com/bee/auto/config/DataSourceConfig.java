package com.bee.auto.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * 配置数据源
 * 
 */
@Configuration
public class DataSourceConfig {

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String userName;

	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.datasource.maxActive}")
    private Integer maxActive;
	
	@Value("${spring.datasource.initialSize}")
    private Integer initialSize;
	
	@Value("${spring.datasource.testWhileIdle}")
    private Boolean testWhileIdle;

	@Bean
	public DruidDataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setMaxActive(maxActive);
		dataSource.setInitialSize(initialSize);
		dataSource.setTestWhileIdle(testWhileIdle);
		dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
		try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException("druid datasource init fail");
        }
		return dataSource;
	}
}