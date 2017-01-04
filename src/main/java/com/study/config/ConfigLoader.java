package com.study.config;

import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLoader extends BaseConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(ConfigLoader.class);
	
	private static ConfigLoader instance = new ConfigLoader();
	
	public static ConfigLoader getInstance() {
		return instance;
	}
	
	private ConfigLoader() {
		reload();
	}
	
	public void reload() {
		try {
			clear();
			Map<String, Object> map = new HashMap<>();
			loadFileProperties("system.properties", map);
			
			/*Add all the stuff to the internal property map.*/
			for(Entry<String, Object> e: map.entrySet()) {
				addPropertyDirect(e.getKey(), e.getValue());
			}
			
			/*Log all the entries*/
			log.info("Final configuration: ");
			Iterator<String> keys = getKeys();
			while(keys.hasNext()) {
				String key = keys.next();
				Object value = getProperty(key);
				
				log.info("Final Config - " + key + " : " + value);
			}
			
		}catch(Exception e) {
			log.error("Error whild loading configuration. " + e);
		}
		
		
	}
	
	private void loadFileProperties(String fileName, Map<String, Object> map) {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			URL url = classLoader.getResource(fileName);
			
			if(url != null) {
				log.info("Found Config file: " + url);
				Configuration conf = new PropertiesConfiguration(fileName);
				Iterator<String> keys = conf.getKeys();
				
				while(keys.hasNext()) {
					String key = keys.next();
					Object value = conf.getProperty(key);
					
					map.put(key, value);
					
					log.info("Load: " + key + " - " + value);
				}
			} else {
				log.info("Cannot find config file: " + fileName);
			}
		} catch (Exception e) {
			log.warn("Error while loading configuration from " + fileName, e);
		}
	}
	
	
}
