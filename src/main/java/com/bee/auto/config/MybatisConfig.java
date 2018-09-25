package com.bee.auto.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import java.util.Properties;

/**
 * mybatis基础配置
 * 
 * @author pom
 *
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {

	@Autowired
	private DruidDataSource dataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactory() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		// 设置datasource
		bean.setDataSource(dataSource);
		// 设置typeAlias(实体类) 包扫描路径
		bean.setTypeAliasesPackage("com.beecloud.auto.bean");
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setUseGeneratedKeys(true);// 使用jdbc的getGeneratedKeys获取数据库自增主键值
		configuration.setUseColumnLabel(true);// 使用列别名替换列名 select user as User
		configuration.setMapUnderscoreToCamelCase(true);// -自动使用驼峰命名属性映射字段 userId user_id
		bean.setConfiguration(configuration);
		// 分页插件

		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql");
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);
		// 添加插件
		bean.setPlugins(new Interceptor[] { pageHelper });
//		 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			// 设置xml扫描路径
			bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
			return bean.getObject();
		} catch (Exception e) {
			throw new RuntimeException("sqlSessionFactory init fail", e);
		}
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate() {
		return new SqlSessionTemplate(sqlSessionFactory());
	}


	//定义事务管理
	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		//绑定一个数据源（库）的事务管理器
		return new DataSourceTransactionManager(dataSource);
	}

}
