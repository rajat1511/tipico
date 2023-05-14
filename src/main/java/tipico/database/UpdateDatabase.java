package tipico.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateDatabase {

	public static void updateDatabse(String table, String database, String dbUrl, String userName, String password,
			String jobTitle, String department, String location) throws SQLException {

		Statement stmt = ConnectDatabase.connectDatabase(dbUrl, userName, password);

		String selectDB = "use " + database;
		String dataCount = "select count(*) from " + table;
		String insertFirstData = "insert into " + table + " values('" + jobTitle + "','" + department + "','" + location
				+ "')";
		String insertData = "insert into " + table + " select '" + jobTitle + "','" + department + "','" + location
				+ "' from " + table + " where not exists  (select * from " + table + " where jobTitle='" + jobTitle
				+ "' and department = '" + department + "' and location ='" + location + "') limit 1";
		stmt.executeUpdate(selectDB);
		ResultSet rs = stmt.executeQuery(dataCount);
		rs.next();
		int count = rs.getInt(1);
		if (count == 0) {
			stmt.executeUpdate(insertFirstData);
		}
		stmt.executeUpdate(insertData);
		stmt.close();
	}

}
