package com.example.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage_Flipkart {

	WebDriver driver;

	public CheckOutPage_Flipkart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(className = "_2Y8lQ1")
	private WebElement address;
	
	public WebElement addressChange() {
		return address;
	}
	
	@FindBy(name="name")
	private WebElement nameOfCustomer;
	
	public WebElement customerName() {
		return nameOfCustomer;
	}
	
	@FindBy(name="phone")
	private WebElement phoneNumber;
	
	public WebElement customerPhoneNumber() {
		return phoneNumber;
	}
	@FindBy(name="pincode")
	private WebElement pincode;
	
	public WebElement pincode() {
		return phoneNumber;
	}
	@FindBy(name="addressLine2")
	private WebElement addressLine2;
	
	public WebElement locality() {
		return addressLine2;
	}
	
	@FindBy(name="addressLine1")
	private WebElement addressLine1;
	
	public WebElement addressArea() {
		return addressLine1;
	}
	@FindBy(xpath = "//input[@name='city']")
	private WebElement city;
	
	public WebElement cityOrtownOrDistrict() {
		return city;
	}
	
	@FindBy(xpath = "//select[@name='state']")
	private WebElement state;
	
	public WebElement stateSelect() {
		return state;
	}
	
	@FindBy(xpath = "//select[@class='_3092M2 _3fgAI3 N25bMB QoXnA9']/option")
	private List<WebElement> listOfStates;
	
	public List<WebElement> stateTobeSelect(){
		return listOfStates;
	}
	
	@FindBy(xpath = "//button[@class='_2AkmmA EqjTfe _7UHT_c']")
	private WebElement saveAndDeliver;
	
	public WebElement saveAndDeliver() {
		return saveAndDeliver;
	}
}
