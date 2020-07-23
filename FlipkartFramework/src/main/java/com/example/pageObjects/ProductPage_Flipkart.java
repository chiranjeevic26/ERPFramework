package com.example.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage_Flipkart {
	WebDriver driver;
	public ProductPage_Flipkart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(name="q")
	private WebElement searchBox;
	
	public WebElement productSearch() {
		return searchBox;
	}

	@FindBy(className = "_3wU53n")
	List<WebElement> listOfProducts;
	
	public List<WebElement> productToSelect(){
		return listOfProducts;
	}

	@FindBy(className = "_2AkmmA _2Npkh4 _2MWPVK")
	private WebElement addToCart;
	
	public WebElement addToCart() {
		return addToCart;
	}
	
	@FindBy(xpath = "//span[@class='_35KyD6']")
	WebElement productText;
	
	public WebElement productText() {
		return productText;
	}
	@FindBy(xpath = "//span[text()='Place Order']")
	private WebElement placeOrder;
	
	public WebElement placeOrder() {
		return placeOrder;
	}
}
