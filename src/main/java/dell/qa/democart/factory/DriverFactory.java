package dell.qa.democart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsMgr;
	
	public static String highlight;
	
	/**
	 * This method is used to initialize the driver based on the given browser name.
	 * @param browserName
	 * @return
	 * this method returns the webdriver.
	 */
	
	public WebDriver init_driver(Properties prop) {
		
		String browserName=prop.getProperty("browser").trim();
		
		highlight=prop.getProperty("highlight").trim();
		optionsMgr=new OptionsManager(prop);
		
		System.out.println("browser name is: "+ browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(optionsMgr.getChromeOptions());
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver(optionsMgr.getFirefoxOptions());
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			driver=new SafariDriver();
		}
		else {
			System.out.println("please pass the right browser name:"+browserName);
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url").trim()); 
		return driver;
	}
	/**
	 * This method is used to initialize the properties
	 * @return
	 * returns property class reference
	 */
	public Properties init_prop() {
		
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
					e.printStackTrace();
							} catch (IOException e) {
						e.printStackTrace();
							}
		return prop;
			
	}
	

	//ThreadLocal -- JDK 8 --> create a local copy of driver
	//set driver with TL
	//getdriver() -- driver
	//dribver null problem
	//u can take ur driver copy anywhere in ur framework
	//better thread management
	//to avoid the dead local conditon -- TL driver copy
	//large test cases count -- 200, 300 TCS --> proper test results
	
	
	/**
	 * take screenshot
	 */
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
	


