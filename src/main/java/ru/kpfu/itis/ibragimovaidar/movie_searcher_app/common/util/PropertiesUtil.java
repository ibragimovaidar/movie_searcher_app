package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

	private final static Properties PROPERTIES;

	static {
		PROPERTIES = new Properties();
		try {
			PROPERTIES.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			LOGGER.error("Properties load error", e);
			throw new RuntimeException(e);
		}
	}

	public static String getProperty(String key){
		String value = PROPERTIES.getProperty(key);
		if (value == null){
			throw new IllegalArgumentException("Property with given key not found");
		}
		return value;
	}

	public static Properties getProperties(){
		return PROPERTIES;
	}

	private PropertiesUtil(){
		throw new RuntimeException();
	}
}
