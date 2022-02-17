package dell.qa.democart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import dell.qa.democart.factory.DriverFactory;
import dell.qa.democart.pages.LoginPage;
import dell.qa.democart.pages.MyAccountPage;
import dell.qa.democart.pages.ProductInfoPage;
import dell.qa.democart.pages.RegisterPage;
import dell.qa.democart.pages.SearchResultPage;
import dell.qa.democart.pages.ShoppingCartPage;

public class BaseTest {
	
	//here we have to give pre and post annotations.
	
	DriverFactory df; //declared at the class level , so that it can be used by diff methods
	WebDriver driver;
	Properties prop;
	
	//all the page class references we will maintain in BaseTest
	
	LoginPage loginPage;
	MyAccountPage accPage;
	RegisterPage regPage;
	SearchResultPage resultPage;
	ProductInfoPage prodInfoPage;
	SoftAssert softassert;
	ShoppingCartPage shopCartPage;
	
	@BeforeTest
	public void setUp() {
		
		df=new DriverFactory();//creating the object of DriverFactory
		prop = df.init_prop();		
		driver= df.init_driver(prop);//initializeing the browser. this method returns Webdriver. so driver reference is used.
										//created one object and two references
		
		loginPage = new LoginPage(driver);//creating object of LoginPage class
		softassert=new SoftAssert();
		
			}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
