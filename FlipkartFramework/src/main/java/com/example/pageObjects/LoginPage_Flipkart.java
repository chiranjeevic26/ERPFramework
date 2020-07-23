package com.example.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_Flipkart {
	WebDriver driver;

	public LoginPage_Flipkart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(className="_3Ep39l")
	private WebElement login;
	
	public WebElement signIn() {
		return login;
		
	}
	
	@FindBy(xpath = "//input[@class='_2zrpKA _1dBPDZ']")
	private WebElement userName;
	
	public WebElement userName() {
		return userName;
	}

	@FindBy(xpath = "//input[@class='_2zrpKA _3v41xv _1dBPDZ']")
	private WebElement password;
	
	public WebElement password() {
		return password;
	}
	
	@FindBy(xpath = "//button[@class='_2AkmmA _1LctnI _7UHT_c']")
	private WebElement loginButton;
	
	public WebElement submit() {
		return loginButton;
	}
}
