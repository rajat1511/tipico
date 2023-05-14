package tipico.database;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {

	public static void createDatabaseIfNotExist(String table, String database, String dbUrl, String userName, String password)
			throws SQLException {

		Statement stmt = ConnectDatabase.connectDatabase(dbUrl, userName, password);
		String createDB = "create database if not exists " + database;
		String selectDB = "use " + database;
		String createTable = "create table if not exists " + table
				+ "(jobTitle varchar(100), department varchar(20), location varchar(20))";
		stmt.executeUpdate(createDB);
		stmt.executeUpdate(selectDB);
		stmt.executeUpdate(createTable);
		stmt.close();
	}

}
