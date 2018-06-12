package db;

import db.mysql.MySQLConnection;

public class DBConnectionFactory {
	private static final String DEFAULT_DB = "mysql";

	public static DBConnection getDBConnection(String db) {
		if(db == "mysql") {
			return new MySQLConnection();
		}
		else {
		    throw new IllegalArgumentException("Invalid db" + db);
		}
	}
	
	public static DBConnection getDBConnection() {
		return getDBConnection(DEFAULT_DB);
	}
}
