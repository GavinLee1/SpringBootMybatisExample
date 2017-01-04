package com.study.mybatis.db;

import java.sql.SQLException;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.dbutils.DbUtils;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.sql.Connection;

public class DataSourceFactory {
	private static final Logger log = LoggerFactory.getLogger(DataSourceFactory.class);
	
	public static DataSource newTomcatConnectionPool(Configuration conf, String jmxName) {
		String driver = conf.getString("db.driver");
		String jdbcUrl = conf.getString("db.jdbc.url");
		String userName = conf.getString("db.username");
		String password = conf.getString("db.password");
		
		int maxConnection = conf.getInt("db.max.connection", 50);
		
		log.info("Using max connection : " + maxConnection);
		
		PoolProperties pool = new PoolProperties();
		pool.setUrl(jdbcUrl);
		
		if(userName != null) pool.setUsername(userName);
		if(password != null) pool.setPassword(password);
		
		pool.setMaxActive(maxConnection);
		pool.setDriverClassName(driver);
		pool.setMinEvictableIdleTimeMillis(8000);
		pool.setMaxAge(5 * 60 * 1000);//5mins
		pool.setValidationQuery("select 315");
        pool.setTestWhileIdle(true);
        pool.setTestOnBorrow(true);
        pool.setValidationInterval(conf.getLong("db.validation.interval.millis", 30000));

        pool.setRemoveAbandoned(conf.getBoolean("db.remove.abandoned", false));
        pool.setRemoveAbandonedTimeout(120); // 2mins
        pool.setLogAbandoned(conf.getBoolean("db.log.abandoned", false));
		
		String initSQL = conf.getString("db.init.sql", null);
		if(initSQL != null) {
			pool.setInitSQL(initSQL);
		}
		
		DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource(pool);
		
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
		
		if(jmxName != null) {
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			try {
				ObjectName mname = new ObjectName("com.mozat.db.type=DataSource, name=" + jmxName);
				((org.apache.tomcat.jdbc.pool.DataSource) ds).preRegister(mbs, mname);
			} catch (Exception e)
			{
				log.error(e.getMessage(), e);
			}
		}
		
		return ds;
	}
	
	public static DataSource newTomcatConnectionPool(Configuration conf) {
        return newTomcatConnectionPool(conf, null);
    }
}
