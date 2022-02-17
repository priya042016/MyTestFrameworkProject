package dell.qa.democart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dell.qa.democart.utils.Constants;

public class MyAccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		}
	@Test
	public void accountPageTitleTest() {
		
		String actTitle=accPage.getMyAccountPageTitle();
		System.out.println("Account page title is: "+actTitle);
		Assert.assertEquals(actTitle, Constants.MY_ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void accountPageUrlTest() {
		String actUrl=accPage.getMyAccountPageUrl();
		System.out.println("Account page URL is: "+actUrl);
		Assert.assertTrue(actUrl.contains(Constants.MY_ACCOUNT_PAGE_URL_FRACTION));
	
	}
	
	@Test
	public void accountPageHeaderTest() {
		String actHeader = accPage.getMyAccountPageHeader();
		System.out.println("Account page header is: "+actHeader);
		Assert.assertEquals(actHeader, Constants.MY_ACCOUNT_PAGE_HEADER);
	}
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	@Test
	public void isSearchExistTest() {
		Assert.assertTrue(accPage.searchExist());
	}
	
	@Test
	public void accPageSectionsTest() {
		List<String> actSecList=accPage.getAccPageSections();
		System.out.println("Actual Section List: "+actSecList);
		Assert.assertEquals(actSecList, Constants.MY_ACCOUNT_PAGE_SECTIONS);
		
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] { 
			{"Macbook"},{"iMac"},{"Apple"}
							};
	}
	
	@Test(dataProvider = "productData")
	public void searchTest(String productName) {
		resultPage = accPage.doSearch(productName);
		int prodCount = resultPage.getProductListCount(productName);
		Assert.assertTrue(prodCount>0);
	}
	
	@DataProvider
	public Object[][] selectProductData() {
		return new Object[][] { 
			{"Macbook", "MacBook Pro"},
			{"iMac", "iMac"},
			};
		
	}
	
	@Test(dataProvider = "selectProductData")
	public void selectProductTest(String prodName, String MainProdName) {
		resultPage = accPage.doSearch(prodName);
		prodInfoPage  = resultPage.selectProduct(MainProdName);
		Assert.assertEquals(prodInfoPage.getProductHeaderName(), MainProdName);
		
	}
	
	
	
}
