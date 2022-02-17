package dell.qa.democart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dell.qa.democart.utils.Constants;
import dell.qa.democart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1. private By locator
		private By emailId = By.id("input-email");
		private By password = By.id("input-password");
		private By loginBtn = By.xpath("//input[@value='Login']");
		private By forgotPwdLink = By.linkText("Forgotten Password");
		private By registerLink=By.linkText("Register");

		// 2. page constructor:
		public LoginPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}

		// 3. public page actions/methods:
		public String getLoginPageTitle() {
			return eleUtil.doGetPageTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		}

		public String getLoginPageUrl() {
			return eleUtil.waitForURLContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
		}

		public boolean isForgotPwdLinkExist() {
			return eleUtil.doIsDisplayed(forgotPwdLink);
		}

		public MyAccountPage doLogin(String userName, String pwd) {
			eleUtil.doSendKeys(emailId, userName);
			eleUtil.doSendKeys(password, pwd);
			eleUtil.doClick(loginBtn);
			return new MyAccountPage(driver);
		}
		
		public RegisterPage goToRegisterPage() {
			eleUtil.doClick(registerLink);
			return new RegisterPage(driver);
			
		}

	}








