package practice.Framework_Appium;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.FormPage;
import resources.TestData;

public class ApiDemoTest extends Base{
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException
	{
	//taskkill /F /IM node.exe
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
		
	}

	@Test(dataProvider = "InputData", dataProviderClass =TestData.class )
	public static void apiDemo(String input) throws Exception {
		// TODO Auto-generated method stub
		service=startServer();
		//service.start();
		AndroidDriver<AndroidElement>  driver =capabilitiesSet("apiDemo");
		
		FormPage fm=new FormPage(driver);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 fm.preferences.click();
		 fm.defendencies.click();
		 //driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
	    // driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
	    // driver.findElementById("android:id/checkbox").click();
		 fm.checkBox.click();
	    // driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		 fm.relative.click();
		 
	    // driver.findElementByClassName("android.widget.EditText").sendKeys("hello");
	     fm.edit().sendKeys(input);
	     driver.findElementsByClassName("android.widget.Button").get(1).click();
	    // fm.buttons.get(1).click();
		/*
		 * driver.findElementByXPath("//android.widget.Button[@text='Ok']").click();
		 * driver.findElementByXPath("//android.widget.TextView[@text='Preference']").
		 * click();
		 */
		 service.stop();
	}

}
