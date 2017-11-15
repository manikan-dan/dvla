package DDT;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dvlaTest {
	
	
	@Test(dataProvider="wordpressData")
	public void loginToWordpress(String username, String regnNumber)
	{
		//WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "/Users/Jokrish/Documents/System_Tools/Selenium/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		String baseUrl="https://vehicleenquiry.service.gov.uk/";
		
		driver.get(baseUrl);
	//	String s=username;
		System.out.println(username+ "\t "+regnNumber );
		driver.findElement(By.id("Vrm")).sendKeys(username);
		driver.findElement(By.name("Continue")).click();
		String color = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[3]/span[2]/strong")).getText();
		String make = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[2]/span[2]/strong")).getText();
		String number = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[1]/span[2]")).getText();
		
		
		System.out.println(color+"\t"+make+"\t"+number);
	//	System.out.println(make);
	//	System.out.println(number);
		
		
	//	WebElement numberplate=driver.findElement(By.xpath(".//*[@id='Vrm']"));
	//	System.out.println(numberplate);
		driver.quit();
	
	}
	

	@DataProvider(name="wordpressData")
	public Object[][] passData()
	{
	
	String excelpath=("/Users/Jokrish/Documents/Test/Excel_Test_Data.xlsx");
	ExcelConfig obj1 = new ExcelConfig(excelpath);
	int row = obj1.getRowCount(0);
	Object[][] data = new Object[row][2];
	for (int i=0;i<row;i++)
	{
		data[i][0]=obj1.getData(0, i, 0);
		data[i][1]=obj1.getData(0, i, 1);
	}
	return data;
	}
}
