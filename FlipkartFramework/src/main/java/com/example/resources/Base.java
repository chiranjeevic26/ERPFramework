package com.example.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Base {
	
	static WebDriver driver;
	Platform WIN10;
	String nodeURL;
	private static XSSFSheet ExcelWSheet;
	 
	 private static XSSFWorkbook ExcelWBook;
	 
	

	@Parameters({"portNO","appURL"})
	@BeforeMethod()
	public void setUp(String portNO, String appURL) throws InterruptedException, NullPointerException, MalformedURLException
	{			
		if(portNO.equalsIgnoreCase("4545"))
		{
			nodeURL = "http://192.168.0.105:4545/wd/hub";
			System.out.println("Firefox Browser Test Environment created");
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.WINDOWS);
			
			driver = new RemoteWebDriver(new URL(nodeURL),cap);			
			driver.manage().window().maximize();
			driver.navigate().to(appURL);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else 
		if(portNO.equalsIgnoreCase("4546"))
		{
			nodeURL = "http://192.168.0.105:4546/wd/hub";
			System.out.println("Chrome Browser Test Environment created");
			DesiredCapabilities cap1 = DesiredCapabilities.chrome();			
			cap1.setBrowserName("chrome");
			cap1.setPlatform(Platform.WINDOWS);
			
			driver = new RemoteWebDriver(new URL(nodeURL),cap1);			
			driver.manage().window().maximize();
			driver.navigate().to(appURL);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else
		if(portNO.equalsIgnoreCase("4547"))
		{
			nodeURL = "http://192.168.0.105:4547/wd/hub";
			System.out.println("Internet Browser Test Environment created");
			DesiredCapabilities cap2 = DesiredCapabilities.internetExplorer();
			cap2.setBrowserName("internet explorer");
			cap2.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			cap2.setPlatform(Platform.WINDOWS);
			
			driver = new RemoteWebDriver(new URL(nodeURL),cap2);			
			driver.manage().window().maximize();
			driver.navigate().to(appURL);	
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else	
        System.err.println("Provide correct port no");
	}
	
	/*
	 * @DataProvider(name="Authentication") public Object[][] credentials() {
	 * Object[][] obj = new Object[3][2]; obj[0][0]="Admin"; obj[0][1]="Admin";
	 * 
	 * obj[1][0]="Tester"; obj[1][1]="Tester";
	 * 
	 * obj[2][0]="Developer"; obj[2][1]="developer";
	 * 
	 * return obj; }
	 */	
	public static HashMap<String, String> readPropertiesToMap(String propertyFile){
		HashMap<String, String> propertyData=new HashMap<>();
		
		try {
			FileInputStream fi=new FileInputStream(propertyFile);
			Properties propFile=new Properties();
			propFile.load(fi);
			
			Set<Object> allkeys=propFile.keySet();
			for(Object key:allkeys) {
				String propValue=propFile.getProperty(key.toString());
				propertyData.put(key.toString(), propValue);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertyData;
		
	}
	
	//read file and save it in Hash map
  	public static HashMap<String, List<String>> readFileOnce(String name) throws FileNotFoundException {
  		HashMap<String, List<String>> data = new LinkedHashMap<>();
  		File file = new File(System.getProperty("user.dir")+"//testData//Flipkart_TestData.xlsx");
  		FileInputStream ExcelFile = new FileInputStream(file);
  		List<Object> testList = new ArrayList<Object>();
  		try

  		{
  			
  			ExcelWBook = new XSSFWorkbook(ExcelFile);
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		//ExcelWSheet = ExcelWBook.getSheetAt(0);
  		ExcelWSheet = ExcelWBook.getSheet(name);
  		int Row = ExcelWSheet.getLastRowNum();
  		int Cell = ExcelWSheet.getRow(0).getLastCellNum();
  		int k = 0;
  		
  		
  		for (int i = 0; i < Cell; i++) {
  			List<String> list = new ArrayList<String>();
  			String key = ExcelWSheet.getRow(k).getCell(i).getStringCellValue();
  			for (int j = 1; j <= Row; j++) {
  				

  				XSSFRow row = ExcelWSheet.getRow(j);
  				XSSFCell cell = row.getCell(i);
  				if (cell != null) {
  					if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
  						// int value = (int) ExcelWSheet.getRow(i).getCell(j).getNumericCellValue();
  						int value = (int) ExcelWSheet.getRow(j).getCell(i).getNumericCellValue();
  						
  						//String.valueOf(value) ;
  						list.add(String.valueOf(value));

  					} else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
  						list.add(ExcelWSheet.getRow(j).getCell(i).getStringCellValue());
  					}
  				} else {
  					list.add("");
  				}
  				//System.out.println(list);
  			}
  			
  			data.put(key, list);
  		
  		}

  		return data;

  		
  	}
  	  	public static void wait5Seconds() {
		long start = new Date().getTime();
		while (new Date().getTime() - start < 5000) {

		}
	}

	public static void wait2Seconds() {
		long start = new Date().getTime();
		while (new Date().getTime() - start < 2000) {

		}
	}
	public void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", element);

	}

	public static void scrollToElementAction(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).perform();

	}

	public static void scrollToElementAndClick(WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element).click().build().perform();
	}
	@AfterMethod()
	public void tearDown()
	{
			driver.quit();
			System.out.println("Closing the Browser");
			System.out.println("**************************************************************");
	}
}
