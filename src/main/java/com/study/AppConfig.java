package com.study;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.study.config.ConfigLoader;
import com.study.mybatis.db.DataSourceFactory;

@Configuration
@EnableScheduling
public class AppConfig {

	@Bean
	public DataSource dataSource() {
		return DataSourceFactory.newTomcatConnectionPool(ConfigLoader.getInstance().subset("mysql"));
	}
}
