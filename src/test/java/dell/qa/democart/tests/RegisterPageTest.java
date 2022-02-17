package dell.qa.democart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dell.qa.democart.utils.Constants;
import dell.qa.democart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{

	
	
	@BeforeClass
	public void regSetup() {
		regPage = loginPage.goToRegisterPage();
	}
	
	public String getRandomNumber() {
		Random randomGen =  new Random();
		String email= "testautomationNov2021"+randomGen.nextInt(1000)+"@gmail.com";
		return email;
	
	}
	
	@DataProvider
	//return type of DataProvider is 2D array.
	public Object[][] getRegisterData() {
		
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return regData;
	}
	
		
	@Test(dataProvider = "getRegisterData")//doing data mapping
	public void userRegistrationTest(String firstName, String lastName,	String telephone, String password, String subscribe) {
		
		Assert.assertTrue(regPage.accountRegistration(firstName, lastName, getRandomNumber(), telephone, password, subscribe));
		
	
	}
	
}
