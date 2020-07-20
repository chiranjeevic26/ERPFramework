package practice.Framework_Appium;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	 public static AppiumDriverLocalService service;
	 public static AndroidDriver<AndroidElement>  driver;
	 public static AppiumDriverLocalService startServer()
		{
			//
		boolean flag=	checkIfServerIsRunnning(4723);
		if(!flag)
		{
			
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
			return service;
			
		}
		
	public static boolean checkIfServerIsRunnning(int port) {
			
			boolean isServerRunning = false;
			ServerSocket serverSocket;
			try {
				serverSocket = new ServerSocket(port);
				
				serverSocket.close();
			} catch (IOException e) {
				//If control comes here, then it means that the port is in use
				isServerRunning = true;
			} finally {
				serverSocket = null;
			}
			return isServerRunning;
		}
	public static void startEmulator() throws IOException, InterruptedException
	{

		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\startEmulator.bat");
		Thread.sleep(6000);
	}

	public static AndroidDriver<AndroidElement> capabilitiesSet(String app) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		/*
		 * File f=new File("resource"); File fs=new File(f,app);
		 */
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/"+"resource/config.properties");
		Properties pr=new Properties();
		pr.load(fis);
		
		AndroidDriver<AndroidElement> driver;
		
		File f=new File("resource");
		File fs=new File(f,(String) pr.get(app));
		String device=(String) pr.get("emulator");
		DesiredCapabilities capabilities =new DesiredCapabilities();
		if(device.contains("Emulator")) {
			startEmulator();
		}
		
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		
		/*
		 * else if(device.equalsIgnoreCase("real")) {
		 * capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
		 * "Android Device"); }
		 */
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		
		driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
			return driver;
		
	}
	
	public static void getScreenshot(String s) throws IOException
	{
	File scrfile=	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrfile,new File(System.getProperty("user.dir")+"\\"+s+".png"));
	
	}
	public void stopServer() {
		 service.stop();
	}
}
