package dell.qa.democart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import dell.qa.democart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By productHeaderName = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By prodctMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By productQty=By.id("input-quantity");
	private By addToCartBtn=By.id("button-cart");
	private By successMsg=By.xpath("//div[@id='product-product']//div[contains(text(), 'Success:')]");
	private By dismissBtn=By.xpath("//button[@type='button' and @class='close']");
	private By cartTotal=By.id("cart-total");
	private By emptyMsg=By.xpath("//p[contains(text(),'empty')]");
	private By viewCartLink=By.linkText("View Cart");
	
	Map<String, String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	public String getProductHeaderName() {
		String actualHeader = eleUtil.doGetText(productHeaderName).trim();
		System.out.println("The product header is: " +actualHeader);
		return actualHeader;
	}
	
	public int getProductImageCount() {
		return eleUtil.waitForElementsVisible(productImages, 10).size();
	}
	
	public Map<String, String> getProductInfo() {
		productMap=new LinkedHashMap<String , String>();//use LinkedHashMap for the sorted order output
		productMap.put("Name", getProductHeaderName());
		productMap.put("Images", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getPricingData();
		return productMap;
	}
	
//	Product Meta Data
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(prodctMetaData);
		for(WebElement e: metaDataList) {
			String text=e.getText();
			String meta[]=text.split(":");
			String key=meta[0].trim();
			String value=meta[1].trim();
			productMap.put(key, value);
		}
		
	}
//	$2,000.00
//	Ex Tax: $2,000.00
	private void getPricingData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price=priceList.get(0).getText().trim();
		String exTax=priceList.get(1).getText().trim();
		productMap.put("Price", price);
		productMap.put("ExtraTax", exTax);
		
	}
	
	public boolean addTocart(String productQty) {
		eleUtil.doSendKeys(this.productQty, productQty);
		System.out.println("Product qty is: "+ productQty);
		if(!productQty.equals("0")) {
			eleUtil.doClick(addToCartBtn);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else {
				System.out.println("Please enter qty");
					}
			if(eleUtil.doIsDisplayed(successMsg)) {
			String successMessage = eleUtil.doGetText(successMsg);
			System.out.println(successMessage);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			eleUtil.doClick(dismissBtn);
						
			return true;
				}
			
			return false;	
		
	}
	
	public boolean getTotalItemsOfCart() {
		
		eleUtil.doClick(cartTotal);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if(eleUtil.doGetText(cartTotal).contains("0 item(s)")){
			String emptyMessage= eleUtil.doGetText(emptyMsg);
			System.out.println(emptyMessage);
			return false;
				}
		else {
			String totalItems=eleUtil.doGetText(cartTotal);
			System.out.println("The total items in the cart with price is: "+totalItems);
			List<WebElement> rowVal=driver.findElements(By.xpath("//table[contains(@class, 'table-striped')]//tr"));
			//List<String > contactrowVal=new ArrayList<String>();
			System.out.println(rowVal.size());
//			for(WebElement e:rowVal) {
//				String text=e.getText();
//				contactrowVal.add(text);
//						}
//			System.out.println(contactrowVal+" ");
			
			for(int i=0; i<rowVal.size(); i++) {
				String text=rowVal.get(i).getText();
				System.out.print(text+" ");
				System.out.println();
			}
			return true;
		
		}
		
	}
	
	public ShoppingCartPage viewCartPage() {
		eleUtil.doClick(cartTotal);
		eleUtil.waitForElementVisible(viewCartLink, 5);
		eleUtil.doClick(viewCartLink);
		return new ShoppingCartPage(driver);
	}
	
	
}
