package tipico.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetDatabse {

	public static int getJobCountFromDatabse(String table, String database, String dbUrl, String userName, String password)
			throws SQLException {

		Statement stmt = ConnectDatabase.connectDatabase(dbUrl, userName, password);
		String selectDB = "use " + database;
		String dataCount = "select count(*) from " + table;
		stmt.executeUpdate(selectDB);
		ResultSet rs = stmt.executeQuery(dataCount);
		rs.next();
		int count = rs.getInt(1);
		stmt.close();
		return count;

	}

}
