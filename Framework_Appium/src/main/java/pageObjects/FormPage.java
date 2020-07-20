package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {

	public FormPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
	public WebElement preferences;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
	public WebElement defendencies;
	
	@AndroidFindBy(id="android:id/checkbox")
	public WebElement checkBox;
	
	@AndroidFindBy(xpath = "(//android.widget.RelativeLayout)[2]")
	public WebElement relative;
	
	@AndroidFindBy(className = "android.widget.EditText")
	private WebElement edit;
	
	@AndroidBy(className = "android.widget.Button")
	public List<WebElement> buttons;
	
	public List<WebElement> clickOnbutton(){
		return buttons;
	}
 	
	public WebElement edit() {
		return edit;
	}
	
}
