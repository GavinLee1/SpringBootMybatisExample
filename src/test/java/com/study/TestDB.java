package com.study;
import javax.sql.DataSource;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.study.mybatis.daoManager.DaoManager;

public class TestDB {
	public static DataSource h2DataSource() {
		EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.setName("rings_event")
				.setScriptEncoding("UTF-8")
				.ignoreFailedDrops(true)
				.addScript("schema.sql")
				.addScripts("test_data.sql")
				.build();
		return db;
	}
	
	public void main(String[] args) {
		DaoManager daoManager = new DaoManager(h2DataSource());
	}
}
