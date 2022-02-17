package dell.qa.democart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import dell.qa.democart.factory.DriverFactory;

public class ElementUtil {
	
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		jsUtil=new JavaScriptUtil(driver);
	}
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	
	public boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}
	
	public  void doClick(By locator) {
		getElement(locator).click();
	}
	
	//do click by string locators
	public  void doClick(String locatortype, String locatorvalue) {
		getElement(getBy(locatortype, locatorvalue)).click();
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public  String doGetAttribute(By locator, String attName) {
		return getElement(locator).getAttribute(attName);
				}
	
	
	public  By getBy(String locatortype, String locatorvalue) {
		By locator=null;
		switch (locatortype.toLowerCase()) {
		case "id":
			locator=By.id(locatorvalue);
			break;
		case "name":
			locator=By.name(locatorvalue);
			break;
		case "classname":
			locator=By.className(locatorvalue);
			break;
		case "xpath":
			locator=By.xpath(locatorvalue);
			break;
		case "css":
			locator=By.cssSelector(locatorvalue);
			break;
		case "linktext":
			locator=By.linkText(locatorvalue);
			break;
		case "partiallinktext":
			locator=By.partialLinkText(locatorvalue);
			break;
		case "tagname":
			locator=By.tagName(locatorvalue);
			break;

		default:
			break;
		}
		return locator;
	}
	
	
	//here the common methods should not be static. bcoz it cant be given to multiple tests at a time as it is present in common memory system.
	public  WebElement getElement(By locator) {
		WebElement ele= driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);
			}
		return ele;
	}
	//using By locator
	public  void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	//using String locator
	public void doSendKeys(String locatortype, String locatorvalue, String value) {//By locator, "inuput-email", "pri@gmail.com"
		getElement(getBy(locatortype, locatorvalue)).sendKeys(value);
		
	}
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public int getElementsCount(By locator) {
		return getElements(locator).size();
		
	}
	
	public void printElementsText(By locator) {
		List<WebElement> elementList=getElements(locator);
		for(WebElement e:elementList) {
			String text=e.getText();
			System.out.println(text);
		}
	}
	
		/**
		 * This method will return the list of element's text
		 * @param locator
		 * @return
		 */
		public List<String> getElementsTextList(By locator) {
			List<WebElement> elementList=getElements(locator);
			//empty string arraylist to store the text values
			List<String> eleTextList=new ArrayList<String>();
			for(WebElement e:elementList) {
				String text=e.getText();
				if(!text.isEmpty()) {
				eleTextList.add(text);
				 }
				}
			return eleTextList;
				
			
		}
		/**
		 * This method will return the element's attribute values
		 * @param locator
		 * @return
		 */
		
		public List<String> getElementsAttributeList(By locator, String attrName) {
			List<WebElement> eleList=getElements(locator);
			
			List<String> eleAttrList=new ArrayList<String>();
			for(WebElement e:eleList) {
				String attrVal=e.getAttribute(attrName);
				eleAttrList.add(attrVal);
				
			}
			return eleAttrList;
		}
	/**
	 * This method will click on particular text link
	 * @param locator
	 * @param linkText
	 */
		public void clickOnLink(By locator, String linkText) {
			List<WebElement> langList=getElements(locator);
			System.out.println(langList.size());
			for(WebElement e:langList) {
				String text=e.getText();
				if(text.contains(linkText)) {
					e.click();
					break;
				}
			}
			
		}
		
		/***********************************SelectDropDown utils***********************************/
		
		public  void doSelectDropDownByIndex(By locator, int index) {
			Select select=new Select(getElement(locator));
			select.selectByIndex(index);
		}
		
		public  void doSelectDropDownByVisibleText(By locator, String visibletxt) {
			Select select=new Select(getElement(locator));
			select.selectByVisibleText(visibletxt);
		}
		
		public  void doSelectDropDownByValue(By locator, String value) {
			Select select=new Select(getElement(locator));
			select.selectByValue(value);
		}
		
		public  void selectDropDownValue(By locator, String value) {
			Select select=new Select(getElement(locator));
			List<WebElement> optionsList=select.getOptions();
			for(WebElement e:optionsList) {
				String text=e.getText();
				if(text.contains(value)) {
					e.click();
					break;
				}
			}
		}
		
		public int getDropDownOptionsCount(By locator) {
			Select select=new Select(getElement(locator));
			return select.getOptions().size();
			
		}
		/**
		 * This method will return the list of drop down options
		 * @param locator
		 * @return
		 */
		public  List<String> getDropDownOptionsList(By locator) {
			Select select=new Select(getElement(locator));
			List<WebElement> optionsList=select.getOptions();
			//create one string array list to store the text of all drop down options
			List<String> optionsTxtList=new ArrayList<String>();
			System.out.println(optionsList.size());
			
			for (WebElement e:optionsList) {
				String text=e.getText();
				optionsTxtList.add(text);
						}
			return optionsTxtList;
			
		}
		
		/**********************************Actions Methods*****************************************/
		public  void doActionsSendKeys(By locator, String value) {
			Actions act=new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
		}
		
		/**	Clicks in the middle of the given element.
		 *  Equivalent to: Actions.moveToElement(onElement).click()
		 * 
		 * @param locator
		 */
		public  void doActionsClick(By locator) {
			Actions act=new Actions(driver);
			act.click(getElement(locator)).perform();
			
		}
/**********************************Wait Utils********************************************/
		
		/**
		 * An expectation for checking an element is visible and enabled such that you can click it.
		 * @param locator
		 * @param timeOut
		 * @return 
		 */
		
		public WebElement clickWhenReady(By locator, int timeOut) {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page. This does notnecessarily mean that the element is visible.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementPresence(By locator, int timeOut) {
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			
		}
		
		/**
		 * An expectation for checking that there is at least one element present on a web page.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			
		}
		
		/**
		 * This function uses FluentWait methods
		 * @param locator
		 * @param timeOut
		 * @param pollingTime
		 * @return
		 */
		public  WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(pollingTime))
					.ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class)
					.withMessage(locator + " is not found within the given time......");
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));						
		}
		/**
		 * This is using WebDriver wait which access all its parent (FluentWait) methods.
		 * @param locator
		 * @param timeOut
		 * @param pollingTime
		 * @return
		 */
		
		public  WebElement waitForElementPresenceWithWait(By locator, int timeOut, int pollingTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.pollingEvery(Duration.ofSeconds(pollingTime))
					.ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class)
					.withMessage(locator + " is not found within the given time......");

			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));						
			
			
		}
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page and visible.
		 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
		 * default polling time = 500 ms
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		
	public WebElement waitForElementVisible(By locator, int timeOut) {
			
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
		}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page and visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * default polling time = customized time
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	/**
	 * An expectation for checking that all elements present on the web page that match the locatorare visible. 
	 * Visibility means that the elements are not only displayed but also have a heightand width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
		public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			
		}
		
		
/********************************Non-WebElements(title, url, alert, frame)****************************/
	public  boolean waitForPageTitle(String title, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(title));
	}
	
	public  boolean waitForPageActualTitle(String actTitle, int timeOut) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(actTitle));
	}
	
	public  String doGetPageTileContains(String titleVal, int timeOut) {
		if(waitForPageTitle(titleVal, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}
	
	public  String doGetPageTitleIs(String titleVal, int timeOut) {
		if(waitForPageActualTitle(titleVal, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}
	
	public  String waitForURLContains(String partialURL, int timeOut) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlContains(partialURL))) {
				return driver.getCurrentUrl();
		}
		else return null;
	}
	
	public  String waitForURLToBe(String ActualURl, int timeOut) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if(wait.until(ExpectedConditions.urlToBe(ActualURl))) {
				return driver.getCurrentUrl();
		}
		else return null;
	}
	
/********************************frame Utils*********************************************************/
	public WebDriver waitForFrameByIndex(int timeOut, int index) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));//by index
	}
	
	public WebDriver waitForFrameByLocator(int timeOut, By frameLocator) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	
	public WebDriver waitForFrameByElement(int timeOut, WebElement frameElement) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	
	
	
	



}
