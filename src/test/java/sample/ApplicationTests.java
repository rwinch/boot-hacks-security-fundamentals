package sample;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.web.context.WebApplicationContext;

import sample.pages.AdminPage;
import sample.pages.IndexPage;
import sample.pages.LoginPage;
import sample.pages.MessagePage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@WithMockUser
public class ApplicationTests {
	@Autowired
	WebApplicationContext wac;

	HtmlUnitDriver driver;

	@Before
	public void setup() {
		driver = MockMvcHtmlUnitDriverBuilder
				.webAppContextSetup(wac, springSecurity())
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

		assertThat(message.getMessage()).isEqualTo("Hello user!");
	}

	@WithAnonymousUser
	@Test
	public void messagePageRequiresLogin() {
		LoginPage login = MessagePage.to(driver, LoginPage.class);

		login.assertAt();
	}

	@WithAnonymousUser
	@Test
	public void failLogin() {
		LoginPage login = MessagePage.to(driver, LoginPage.class);

		login = login.login("invalid", "credentials", LoginPage.class);

		login.assertAt();
		login.assertError();
	}

	@WithAnonymousUser
	@Test
	public void successLogin() {
		LoginPage login = MessagePage.to(driver, LoginPage.class);

		MessagePage message = login.login("rob@example.com", "password", MessagePage.class);

		message.assertAt();
	}

	@WithMockUser(roles="ADMIN")
	@Test
	public void adminAllowsAdmin() {
		AdminPage admin = AdminPage.to(driver, AdminPage.class);

		admin.assertAt();
	}
}