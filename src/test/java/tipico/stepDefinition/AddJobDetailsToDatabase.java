package tipico.stepDefinition;

import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import tipico.database.GetDatabse;
import tipico.database.UpdateDatabase;
import tipico.pageObjects.LandingPage;
import tipico.utility.DriverSetup;

public class AddJobDetailsToDatabase {

	WebDriver driver;
	LandingPage landingPage;
	String database;
	String table;
	String username;
	String password;
	String dbUrl;

	@Given("^I have landed on Tipico career page (.+)$")
	public void i_have_landed_on_tipico_careers_page(String url) {

		WebDriver driver = DriverSetup.initiateDriver();
		this.driver = driver;
		landingPage = new LandingPage(driver);
		landingPage.goTo(url);
		landingPage.acceptCookies();

	}

	@When("^I have added all avaliable job details into database (.+) table (.+) with username (.+), password (.+) and dbUrl (.+)$")

	public void i_have_added_all_the_avaliable_job_details_into_database(String database, String table, String username,
			String password, String dbUrl) throws SQLException, InterruptedException {

		this.database = database;
		this.table = table;
		this.username = username;
		this.password = password;
		this.dbUrl = dbUrl;

		landingPage = new LandingPage(driver);
		int paginationCount = landingPage.paginationCount();

		for (int j = 1; j < paginationCount; j++) {

			List<WebElement> jobList = landingPage.jobList();
			List<WebElement> departments = landingPage.department();
			List<WebElement> jobTitles = landingPage.jobTitle();
			List<WebElement> locations = landingPage.location();
			for (int i = 0; i < jobList.size(); i++) {

				String department = departments.get(i).getText();
				String jobTitle = jobTitles.get(i).getText();
				String location = locations.get(i).getText();

				if (location.contains("'")) {
					String[] address = location.split("'");
					location = address[0] + address[1];
				}

				UpdateDatabase.updateDatabse(table, database, dbUrl, username, password, jobTitle, department,
						location);

			}

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String scrollBy = landingPage.scrollBy;
			js.executeScript(scrollBy);
			Thread.sleep(2000);
			List<WebElement> nextPage = landingPage.nextPage();
			if (nextPage.size() > 0) {
				landingPage.goToNextPage();
			}

		}

	}

	@Then("I have verify the available jobs should be equal to jobs added into database")
	public void i_verify_the_available_jobs_should_be_equal_to_jobs_added_into_database()
			throws SQLException, InterruptedException {

		int jobCountOnWeb = Integer.parseInt(driver.findElement(By.xpath("//div/p/strong[last()]")).getText());
		int jobCountInDatabase = GetDatabse.getJobCountFromDatabse(table, database, dbUrl, username, password);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(jobCountOnWeb, jobCountInDatabase);
		closeDriver();
	}

	public void closeDriver() {
		driver.close();
	}

}
