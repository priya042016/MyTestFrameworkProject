package dell.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import dell.qa.democart.utils.Constants;
import dell.qa.democart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	By firstName=By.id("input-firstname");
	By lastName=By.id("input-lastname");
	By email=By.id("input-email");
	By telephone=By.id("input-telephone");
	By password=By.id("input-password");
	By confirmPassword=By.id("input-confirm");
	
	By subscribeYes=By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	By subscribeNo=By.xpath("(//label[@class='radio-inline'])[position()=2]/input");
	
	By agreeChkBox=By.name("agree");
	By continueBtn=By.xpath("//input[@value='Continue']");
	//By createdMsg=By.linkText("Your Account Has Been Created!");
	By successMsg=By.cssSelector("div#content h1");
	
	By logoutLink=By.linkText("Logout");
	By registerLink=By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
		
	}
	
	//page actions
	
	public boolean accountRegistration(String firstName, String lastName, String email, String telephone, String password, String subscribe ) {
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("yes")){
			eleUtil.doClick(subscribeYes);
		}else {
		eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(agreeChkBox);
		eleUtil.doClick(continueBtn);
		String successMessage=eleUtil.doGetText(successMsg);
		System.out.println(successMessage);
		
		if(successMessage.contains(Constants.REGISTER_PAGE_SUCCESS_MESSAGE)) 
		{
			//doing below actions for multiple users registration
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
			}
		return false;
		
				
		
	}
	
	
	
	

}
