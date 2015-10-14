package sample;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.web.context.WebApplicationContext;

import sample.pages.IndexPage;
import sample.pages.MessagePage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {
	@Autowired
	WebApplicationContext wac;

	HtmlUnitDriver driver;

	@Before
	public void setup() {
		driver = MockMvcHtmlUnitDriverBuilder
				.webAppContextSetup(wac)
				.build();
	}

	@Test
	public void indexPage() {
		IndexPage index = IndexPage.to(driver);

		index.assertAt();

		MessagePage message = index.goToMessage(MessagePage.class);

		message.assertAt();
	}

	@Test
	public void messagePage() {
		MessagePage message = MessagePage.to(driver, MessagePage.class);

		assertThat(message.getMessage()).isEqualTo("Hello Boot!");
	}
}