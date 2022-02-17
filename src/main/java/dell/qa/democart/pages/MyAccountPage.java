package dell.qa.democart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dell.qa.democart.utils.Constants;
import dell.qa.democart.utils.ElementUtil;

public class MyAccountPage {
	
	private WebDriver driver;
	private ElementUtil ele;
	
	
	//private By locators
	
	private By header=By.cssSelector("div#logo a");
	private By sections=By.cssSelector("div#content h2");
	private By logout=By.linkText("Logout");
	private By search=By.name("search");
	private By searchBtn=By.cssSelector("div#search button");
	
	public MyAccountPage(WebDriver driver) {
		
		this.driver = driver;
		ele= new ElementUtil(driver);
	}
	
	//page Actions
	
	public String getMyAccountPageTitle() {
		return ele.doGetPageTileContains(Constants.MY_ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getMyAccountPageUrl() {
		return ele.waitForURLContains(Constants.MY_ACCOUNT_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getMyAccountPageHeader() {
		return ele.doGetText(header);
	}
	
	public boolean isLogoutLinkExist() {
		return ele.doIsDisplayed(logout);
	}
	
	public boolean logout() {
		if (isLogoutLinkExist()) {
			ele.doClick(logout);
			return true;
		}
		return false;
	}
		
		public List<String> getAccPageSections() {
		
		List<WebElement> secList=ele.waitForElementsVisible(sections, 10);
		ArrayList<String> secValues=new ArrayList<String>();
		for(WebElement e:secList) {
			String text= e.getText();
			secValues.add(text);
			}
		return secValues;
				
		}	
		
		public boolean searchExist() {
			return ele.doIsDisplayed(search);
		}
		
		public SearchResultPage doSearch(String prodName) {
			if(searchExist()) {
				ele.doSendKeys(search, prodName);
				ele.doClick(searchBtn);
				}
			return new SearchResultPage(driver);
		}
		
		
		
	}


