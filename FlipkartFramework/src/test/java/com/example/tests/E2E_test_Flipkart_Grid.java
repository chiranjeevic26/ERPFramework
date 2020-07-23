package com.example.tests;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.example.pageObjects.CheckOutPage_Flipkart;
import com.example.pageObjects.LoginPage_Flipkart;
import com.example.pageObjects.ProductPage_Flipkart;
import com.example.resources.Base;

public class E2E_test_Flipkart_Grid extends Base {

	WebDriver driver;
	
	HashMap<String, List<String>> loginData;
	LoginPage_Flipkart ln=new LoginPage_Flipkart(driver);
	ProductPage_Flipkart prd=new ProductPage_Flipkart(driver);
	CheckOutPage_Flipkart chk=new CheckOutPage_Flipkart(driver);
	@Test
	public void flipkartLogin() throws InterruptedException, Exception
	{	
		
		loginData=Base.readFileOnce("LoginDetails");
		
		System.out.println("Trying with :"+loginData.get("UserName"));
		System.out.println(driver.getTitle());
		
		Base.wait2Seconds();
		ln.userName().click();
		ln.userName().sendKeys(loginData.get("UserName").get(0));
		ln.password().click();
		ln.password().sendKeys(loginData.get("Password").get(0));
		ln.submit().click();
		
		System.out.println("Login success");
		Base.wait2Seconds();
		
		prd.productSearch().click();
		prd.productSearch().sendKeys(loginData.get("productName").get(0));
		prd.productSearch().sendKeys(Keys.ENTER);
		
		System.out.println("searchable product is :"+loginData.get("productName").get(0));
		Base.wait2Seconds();
		
		prd.productToSelect().get(4).click();
		String selectedProduct=prd.productToSelect().get(4).getText();
		
		for (String handle : driver.getWindowHandles()) {
			 
		    driver.switchTo().window(handle);
		    }
		
		if(prd.productText().getText().contains(selectedProduct)) {
				Base.scrollToElementAndClick(prd.addToCart());
				Base.scrollToElementAndClick(prd.placeOrder());
		}else {
			System.out.println("product mismatch");
		}
		
		
		

	}

	
}
