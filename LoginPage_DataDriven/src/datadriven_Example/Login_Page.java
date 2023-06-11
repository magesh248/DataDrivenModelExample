package datadriven_Example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Login_Page {

	
	 String [][] data= {
		 {"Magesh","Chennai2026"},
		 {"Magesh","Chennai2027"},
		 {"Ramesh","Chennai2028"},
		 {"Magesh","Chennai2029"},
		
	};
	
	@DataProvider(name = "LoginData")
	public String [][] loginDataProvider() {
		
		return data;
	}
	
	@Test(dataProvider ="LoginData" )
	public void login_Area(String Uname,String Pwd)
	
	{
		System.getProperty("webdriver.Chrome.driver","D:\\First_selenium_project\\chromedriver_win32 (2)\\chromedriver.exe");	
		ChromeOptions ops=new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		WebDriver driver =new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.get("https://www.testyou.in/Login.aspx");

		WebElement UserName=driver.findElement(By.id("ctl00_CPHContainer_txtUserLogin"));
		UserName.sendKeys(Uname);

		WebElement Password=driver.findElement(By.id("ctl00_CPHContainer_txtPassword"));
		Password.sendKeys(Pwd);

		WebElement submit=driver.findElement(By.id("ctl00_CPHContainer_btnLoginn"));
		submit.click();
		
		driver.quit();
	}




}
