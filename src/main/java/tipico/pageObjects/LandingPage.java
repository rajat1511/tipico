package tipico.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "_evidon-accept-button")
	WebElement acceptCookies;

	@FindBy(css = ".pagination.justify-content-center li")
	List<WebElement> paginationCount;

	@FindBy(css = "div[class='card card-job']")
	List<WebElement> jobList;

	@FindBy(css = ".job-category a")
	List<WebElement> jobCategory;

	@FindBy(css = ".card-title a")
	List<WebElement> jobTitle;

	@FindBy(css = ".card-subtitle")
	List<WebElement> location;

	@FindBy(css = ".page-next a")
	List<WebElement> nextPage;

	@FindBy(css = ".page-next a")
	WebElement goToNextPage;

	public String scrollBy = "window.scrollBy(0,4000)";

	public void goToNextPage() {
		goToNextPage.click();
		;
	}

	public void goTo(String url) {
		driver.get(url);
	}

	public void acceptCookies() {

		acceptCookies.click();
	}

	public int paginationCount() {
		int numberOfPages = paginationCount.size();
		return numberOfPages;
	}

	public List<WebElement> jobList() {
		List<WebElement> jobsList = jobList;
		return jobsList;
	}

	public List<WebElement> department() {
		List<WebElement> department = jobCategory;
		return department;
	}

	public List<WebElement> jobTitle() {
		List<WebElement> titleOfJob = jobTitle;
		return titleOfJob;
	}

	public List<WebElement> location() {
		List<WebElement> jobLocation = location;
		return jobLocation;
	}

	public List<WebElement> nextPage() {
		List<WebElement> nextPages = nextPage;
		return nextPages;
	}

}
