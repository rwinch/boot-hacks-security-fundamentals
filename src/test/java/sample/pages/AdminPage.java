package sample.pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {
	WebDriver driver;

	@FindBy(tagName = "h1")
	WebElement title;

	WebElement message;

	public AdminPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void assertAt() {
		assertThat(getTitle()).isEqualTo("Admin Message");
	}

	public String getTitle() {
		return this.title.getText();
	}

	public String getMessage() {
		return message.getText();
	}

	public static <T> T to(WebDriver driver, Class<T> destinationPage) {
		driver.get("http://localhost/admin");
		return PageFactory.initElements(driver, destinationPage);
	}
}
