package by.pvt.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class DBUtil {

	private static final String RESOURCE_BUNDLE_NAME = "db";

	private static final Logger LOG = Logger.getLogger(DBUtil.class);

	public static Connection getConnection() {

		ResourceBundle db = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME);
		try {
			Class.forName(db.getString("driver"));

			return DriverManager.getConnection(db.getString("url"), db.getString("username"), db.getString("password"));
		} catch (Exception e) {
			LOG.error("Connection to DB failed", e);
			throw new IllegalArgumentException(e);
		}
	}

}
