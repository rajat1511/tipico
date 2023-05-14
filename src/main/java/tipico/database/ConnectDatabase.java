package tipico.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {

	public static Statement connectDatabase(String dbUrl, String userName, String password) throws SQLException {

		Connection conn = DriverManager.getConnection(dbUrl, userName, password);
		Statement stmt = conn.createStatement();
		return stmt;
	}
}
