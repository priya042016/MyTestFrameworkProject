package dell.qa.democart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dell.qa.democart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	By searchHeader = By.cssSelector("div#content h1");
	By productResults = By.xpath("//div[@class='caption']//a");
	
	public SearchResultPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}
	
	public int getProductListCount(String prodName) {
		
		int prodCount = eleUtil.waitForElementsVisible(productResults, 10).size();
		System.out.println("product count of " +prodName+" is: "+ prodCount);
		return prodCount;
	}
	
	public ProductInfoPage selectProduct(String mainProductName ) {
		System.out.println("The main product name is: "+mainProductName);
		List<WebElement> prodList = eleUtil.waitForElementsVisible(productResults, 10);
		for (WebElement e : prodList) {
			if (e.getText().equals(mainProductName)) {
				e.click();
				break;
							}
			}
		return new ProductInfoPage(driver);
		
	}

}
