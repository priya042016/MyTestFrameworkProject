package dell.qa.democart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dell.qa.democart.pages.ProductInfoPage;
import dell.qa.democart.utils.Constants;

public class ProudctInfoTest extends BaseTest {
	
	@BeforeClass
	public void prodInfoSetUp() {
		
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productHeaderTest() {
		resultPage = accPage.doSearch("Macbook");
		prodInfoPage = resultPage.selectProduct("MacBook Pro");
		String actualHeader = prodInfoPage.getProductHeaderName();
		Assert.assertEquals(actualHeader, "MacBook Pro");
	}
	
	@DataProvider
	public Object[][] selectProductData() {
		return new Object[][] { 
			{"Macbook", "MacBook Pro", Constants.MACBOOK_IMG_COUNT},
			{"Macbook","MacBook Air", Constants.MACBOOK_IMG_COUNT},
			{"iMac", "iMac", Constants.IMAC_IMG_COUNT}
										};
	}
	
	@Test(dataProvider="selectProductData", enabled=false)
	public void productImagesCountTest(String productName, String mainProudctName, int imageCount) {
		resultPage = accPage.doSearch(productName);
		prodInfoPage = resultPage.selectProduct(mainProudctName);
		System.out.println("Total images for the product:"+ mainProudctName +" is: "+ prodInfoPage.getProductImageCount());
		Assert.assertEquals(prodInfoPage.getProductImageCount(), imageCount);
	}
	//HashMap output. order will be random based on the hashcode. Not as per the UI.
//	Brand:Apple
//	Availability:Out Of Stock
//	Price:$2,000.00
//	Images:4
//	Product Code:Product 18
//	ExtraTax:Ex Tax: $2,000.00
//	Reward Points:800
//	Name:MacBook Pro
	
	//LinkedHashMap output(will be as per the given order in UI)
//	Brand:Apple
//	Product Code:Product 18
//	Reward Points:800
//	Availability:Out Of Stock
//	Price:$2,000.00
//	ExtraTax:Ex Tax: $2,000.00
	
	//TreeMap output. It will be in alphabetical order. sorted order
//	Availability:Out Of Stock
//	Brand:Apple
//	ExtraTax:Ex Tax: $2,000.00
//	Images:4
//	Name:MacBook Pro
//	Price:$2,000.00
//	Product Code:Product 18
//	Reward Points:800
	
	@Test
	public void productInfoTest() {
		resultPage = accPage.doSearch("Macbook");
		prodInfoPage = resultPage.selectProduct("MacBook Pro");
		Map<String, String> prodInfo=prodInfoPage.getProductInfo();
		prodInfo.forEach((k,v)->System.out.println(k+":"+v));
		softassert.assertEquals(prodInfo.get("Brand"), "Apple");
		softassert.assertEquals(prodInfo.get("Price"), "$2,000.00");
		softassert.assertEquals(prodInfo.get("Product Code"), "Product 18");
		softassert.assertAll();//mandatory to write this
		
	}
	
	@DataProvider
	public Object[][] addToCartData() {
			return new Object[][] {
				{"Macbook","MacBook Air", "1"},
				{"iMac", "iMac", "1"}
			};
	}
	
	
	@Test(dataProvider="addToCartData")
	public void addToCartTest(String ProdName, String mainProdName, String Qty) {
		resultPage = accPage.doSearch(ProdName);
		prodInfoPage = resultPage.selectProduct(mainProdName);
		Assert.assertTrue(prodInfoPage.addTocart(Qty));
	}
	
	@Test(dataProvider="addToCartData", enabled=false)
	public void getTotalItemsOfCart(String ProdName, String mainProdName, String Qty) {
		resultPage = accPage.doSearch(ProdName);
		prodInfoPage = resultPage.selectProduct(mainProdName);
		prodInfoPage.addTocart(Qty);
		Assert.assertTrue(prodInfoPage.getTotalItemsOfCart());
	}
	


}

