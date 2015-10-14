package sample.pages;

import static org.assertj.core.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
	WebDriver driver;

	@FindBy(tagName = "h1")
	WebElement title;

	WebElement messageLink;

	public IndexPage(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void assertAt() {
		assertThat(getTitle()).isEqualTo("Home");
	}

	public String getTitle() {
		return this.title.getText();
	}

	public <T> T goToMessage(Class<T> destinationPage) {
		this.messageLink.click();
		return PageFactory.initElements(driver, destinationPage);
	}

	public static IndexPage to(WebDriver driver) {
		driver.get("http://localhost/");
		return PageFactory.initElements(driver, IndexPage.class);
	}
}
