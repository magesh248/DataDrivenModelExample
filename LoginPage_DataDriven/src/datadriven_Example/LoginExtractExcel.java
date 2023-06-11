package datadriven_Example;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginExtractExcel{

	WebDriver driver;
//Instead of string we can put object- It will store any data like  any scientific  symbols
	
	String[][] data1=null;

	@DataProvider(name="Login")
	public String[][] Logindata() throws BiffException, IOException {
		data1=GetExcelData();
		return data1;
	}


	public String[][] GetExcelData() throws BiffException, IOException {

		// to point the excel file location
		FileInputStream excel =new FileInputStream("D:\\Testing\\First_selenium_project\\DD_sample.xls");
		// used to get the excel workbook
		Workbook workbook =Workbook.getWorkbook(excel);

		/* to find sheet 
		Having two approch
		1.sheet name 
		2.index page (0) for example sheet1- index value (0),sheet2-index value (1)*/
		Sheet sheet =workbook.getSheet(0);

		// To find the count of rows and column
		int rowCount=sheet.getRows();
		int colunmCount=sheet.getColumns();

		// String Array is used to store 4 * 2 dimensional
		String testData[][] = new String [rowCount-1][colunmCount];// rowCount(5) include heading-1 
                                                                   // colunmCount (2)   

		for(int i=1;i<rowCount;i++) {
			for(int j=0;j<colunmCount;j++) {
				
				testData[i-1][j]=sheet.getCell(j,i).getContents();	
			}
		}
		return testData;
	}
	
	@BeforeTest
	public void launch_chrome() {
		System.getProperty("webdriver.Chrome.driver","D:\\First_selenium_project\\chromedriver_win32 (2)\\chromedriver.exe");	
		ChromeOptions ops=new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
	    driver =new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.get("https://www.codehim.com/demo/login-page-with-username-and-password/?username=sace&password=sd#");
		
	}
	
	

	@Test(dataProvider = "Login")
	public void login_page(String Uname, String Pwd) throws InterruptedException {
		

		WebElement UserName=driver.findElement(By.id("login__username"));
		UserName.sendKeys(Uname);

		
		WebElement Password=driver.findElement(By.id("login__password"));
		Password.sendKeys(Pwd);

		WebElement submit=driver.findElement(By.xpath("/html/body/div/form/div[3]/input"));
		submit.click();
        Thread.sleep(1000);
		
	}
	
	@AfterTest
	public void close_browser() {
		driver.quit();
		
	}
	}
	


