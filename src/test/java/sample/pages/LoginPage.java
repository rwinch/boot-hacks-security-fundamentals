package sample.pages;

import static org.assertj.core.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	@FindBy(tagName = "h1")
	WebElement title;

	WebElement username;

	WebElement password;

	WebElement submit;

	WebElement error;

	WebElement logout;

	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void assertAt() {
		assertThat(getTitle()).isEqualTo("Please Log In");
	}

	public void assertError() {
		assertThat(error.isDisplayed()).isTrue();
	}

	public String getTitle() {
		return this.title.getText();
	}

	public <T> T login(String username, String password, Class<T> destinationPage) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.submit.click();
		return PageFactory.initElements(driver, destinationPage);
	}

	public static LoginPage to(WebDriver driver) {
		driver.get("http://localhost/");
		return PageFactory.initElements(driver, LoginPage.class);
	}
}
