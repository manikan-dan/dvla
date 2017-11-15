package DDT;

	import org.apache.poi.util.SystemOutLogger;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
	import org.openqa.selenium.NoSuchElementException;

	public class testDvla {
				
		@Test(dataProvider="inputData")
		public void dvla_VehicleCheck(String regnNumber, String company, String colour){
		// Set Chrome browser for the test
			System.setProperty("webdriver.chrome.driver", "/Users/Jokrish/Documents/System_Tools/Selenium/drivers/chromedriver");//driver location
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			String baseUrl="https://vehicleenquiry.service.gov.uk/"; //DVLA URL to test
	
		//Launch Browser
			driver.get(baseUrl);
			System.out.println("Vehicle Regn Number  :" +regnNumber); //Display input data received from Excel Sheet
		
		// Pass the input Registration Number and click continue button
			driver.findElement(By.id("Vrm")).sendKeys(regnNumber);
			driver.findElement(By.name("Continue")).click();
		try {	
			int goodResponse = driver.findElements(By.xpath(".//*[@id='pr3']/div/h1")).size();
			int noVehicleFound = driver.findElements(By.xpath(".//*[@id='content']/h3")).size();
			int wrongFormat = driver.findElements(By.xpath(".//*[@id='content']/form/div/div/div[1]/ul/a")).size();
			
			//If Vehicle found - verify the Colour & Make are correct and display the result
				if(goodResponse != 0){	
					System.out.println("Good News !!!   Vehicle Found  :");
					String color = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[3]/span[2]/strong")).getText();
					String make = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[2]/span[2]/strong")).getText();
					String number = driver.findElement(By.xpath(".//*[@id='pr3']/div/ul/li[1]/span[2]")).getText();
					System.out.println("Data from DVLA    :  Vehicle Number:    "+number+"\t Make  : "+make+"\t Colour  :"+color);
					Assert.assertEquals(number, regnNumber);
					Assert.assertEquals(make, company);
					Assert.assertEquals(color, colour);
					System.out.println("Assert Passed   :  "
			                + "It's a Valid Vehicle. Both Colur and Make are correct for the given Vehicle");	
				
			//If Vehicle not found ie input valid format ,but DVLA doesn't have the vehicle details,throw error message	
				}else if(noVehicleFound != 0){
					String error_noVenfound=driver.findElement(By.xpath(".//*[@id='content']/h3")).getText();
					System.out.println("Error  :" + error_noVenfound);
			// If the input given in invalid format- throw error message
			    }else if (wrongFormat != 0){					
					String formatError = driver.findElement(By.xpath(".//*[@id='content']/form/div/div/div[1]/ul/a")).getText();
					System.out.println("Error :  "+ formatError);
					}
			//All other errors
			    else {
			    	System.out.println("Error  : Unknown Error, Pls check the log");
			    }
		
		}catch(NoSuchElementException e){
			  
		   }	
		
		driver.quit();
		}	
		
		

	 // Get Data input excel file
		@DataProvider(name="inputData")
		public Object[][] passData()
		{
		
		String excelpath=("/Users/Jokrish/Documents/Test/DVLA_Test_Data.xlsx");//Excel sheet path
		Excel_config obj1 = new Excel_config(excelpath);
		int row = obj1.getRowCount(0);//Get total rows
		int column = 3;//Pls enter the total number of columns of the Excel sheet
		System.out.println("Total Rows :"+row);
		Object[][] data = new Object[row][column];
		for (int i=0;i<row;i++){
			for(int j=0;j<column;j++)	
				{
					data[i][j]=obj1.getData(0, i, j);
				}
		    }
		return data;
		}
	}


