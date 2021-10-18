package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

	private static final String jdbcUrl = PropertiesUtil.getProperty("db.jdbcUrl");
	private static final String jdbcDriver =  PropertiesUtil.getProperty("db.jdbcDriver");
	private static final String username = PropertiesUtil.getProperty("db.username");
	private static final String password = PropertiesUtil.getProperty("db.password");


	static {
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			LOGGER.error("jdbcDriver class not found", e);
		}
	}

	private static Connection connection;

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()){
				connection = DriverManager.getConnection(jdbcUrl, username, password);
			}
			return connection;
		} catch (SQLException e) {
			LOGGER.error("DB connection establishing error", e);
			throw new RuntimeException(e);
		}
	}


	private ConnectionManager() {
		throw new RuntimeException();
	}
}
