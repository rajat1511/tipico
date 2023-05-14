package tipico.stepDefinition;

import java.sql.SQLException;

import io.cucumber.java.en.Given;
import tipico.database.CreateDatabase;

public class CreateDatabaseIfNoExist {

	@Given("^I want to create a new database with username (.+), password (.+) and dbUrl (.+) and to create a database (.+) and a table (.+) if does not exist$")
	public void i_want_to_create_a_database(String userName, String password, String dbUrl, String database,
			String table) throws SQLException {

		CreateDatabase.createDatabaseIfNotExist(table, database, dbUrl, userName, password);

	}
}