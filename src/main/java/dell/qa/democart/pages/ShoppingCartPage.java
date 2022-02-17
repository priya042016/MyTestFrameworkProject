package dell.qa.democart.pages;

import org.openqa.selenium.WebDriver;

import dell.qa.democart.utils.Constants;
import dell.qa.democart.utils.ElementUtil;

public class ShoppingCartPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getShoppingCartPageUrl() {
		
		return eleUtil.waitForURLContains(Constants.SHOPPING_CART_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
			
	}

}
