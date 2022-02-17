package dell.qa.democart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dell.qa.democart.utils.Constants;

public class ShoppingCartPageTest extends BaseTest {

	@BeforeClass
	public void shoppingCartPageSetup() {
		
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		}
	
	@DataProvider
	public Object[][] shoppingCartData() {
		return new Object[][] {{"iMac", "iMac", "1"}};	
		
	}
	@Test(dataProvider="shoppingCartData")
	public void shoppingCartPageURlTest(String productName, String mainProductName, String Qty) {
		resultPage = accPage.doSearch(productName);	
		prodInfoPage = resultPage.selectProduct(mainProductName);
		prodInfoPage.addTocart(Qty);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shopCartPage=prodInfoPage.viewCartPage();
		String url=shopCartPage.getShoppingCartPageUrl();
		System.out.println("Shopping Cart page url : " + url);
		Assert.assertTrue(url.contains(Constants.SHOPPING_CART_PAGE_URL_FRACTION));
		
		
	}
}
