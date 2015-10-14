package sample.pages;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MessagePage {
	WebDriver driver;

	@FindBy(tagName = "h1")
	WebElement title;

	WebElement message;

	public MessagePage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void assertAt() {
		assertThat(getTitle()).isEqualTo("Message");
	}

	public String getTitle() {
		return this.title.getText();
	}

	public String getMessage() {
		return message.getText();
	}

	public static <T> T to(WebDriver driver, Class<T> destinationPage) {
		driver.get("http://localhost/message");
		return PageFactory.initElements(driver, destinationPage);
	}
}
