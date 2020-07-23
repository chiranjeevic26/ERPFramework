package practice.Framework_Appium;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import resources.Utilities;

import static io.appium.java_client.touch.TapOptions.tapOptions;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

public class EcommerceApp_tc3 extends Base {

	@Test
	public void totalValidation() throws IOException, Exception {
		//service=startServer();
		service=startServer();
		AndroidDriver<AndroidElement> driver = capabilitiesSet("GeneralStoreApp");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");

		driver.hideKeyboard();

		driver.findElement(By.xpath("//*[@text='Female']")).click();

		driver.findElement(By.id("android:id/text1")).click();
		
		Utilities ut=new Utilities(driver);
		ut.scrollingToText("India");

		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"));");

		// driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new
		// UiSelector().scrollable(true).instance(0)).scrollIntoView(new
		// UiSelector().textMatches(\"" + containedText + "\").instance(0))"));

		driver.findElement(By.xpath("//*[@text='India']")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(4000);

		/*
		 * double sum=0; int count=driver.findElements(By.id(
		 * "com.androidsample.generalstore:id/productPrice")).size();
		 * System.out.println(count); for (int i=0;i<count;i++) { String
		 * amount1=driver.findElements(By.id(
		 * "com.androidsample.generalstore:id/productPrice")).get(i).getText();
		 * 
		 * double Amountp1=getAmount(amount1); sum=sum+Amountp1;
		 * 
		 * }
		 * 
		 * 
		 * String amount1=driver.findElements(By.id(
		 * "com.androidsample.generalstore:id/productPrice")).get(0).getText();
		 * 
		 * double Amountp1=getAmount(amount1); System.out.println(Amountp1);
		 * 
		 * String amount2=driver.findElements(By.id(
		 * "com.androidsample.generalstore:id/productPrice")).get(1).getText();
		 * 
		 * double amountp2=getAmount(amount2); System.out.println(amountp2);
		 * 
		 * System.out.println(sum);
		 * 
		 * 
		 * String totalSum=
		 * driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
		 * .getText(); totalSum=totalSum.substring(1); double
		 * sumOfTotal=Double.parseDouble(totalSum);
		 * 
		 * Assert.assertEquals(sum, sumOfTotal); // Assert.assertEquals(sumOfTotal,
		 * sumofProducts);
		 */
		// gestures

		WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(checkbox))).perform();

		WebElement tc = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		t.longPress(longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release().perform();

		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(7000);
		Set<String> contextNames=driver.getContextHandles();
		for(String contextName:contextNames) {
			System.out.println(contextName);
		}
		
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("hello");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));

	}

	public static double getAmount(String value) {

		value = value.substring(1);
		double updatedAmount = Double.parseDouble(value);
		return updatedAmount;
	}
}
