package com.study;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.study.config.ConfigLoader;

@SpringBootApplication
public class ApplicationMain {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		
		Map<String, Object> props = new HashMap<>();
		Iterator<String> keys = ConfigLoader.getInstance().getKeys();
		
		while(keys.hasNext()) {
			String key = keys.next();
			props.put(key, ConfigLoader.getInstance().getString(key));
		}
		
		new SpringApplicationBuilder()
		.sources(ApplicationMain.class)
		.properties(props)
		.run(args);

	}
}
