package servicenow;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		String url= "https://dev62656.service-now.com/";
		
		driver.get(url);
		
		driver.manage().window().maximize();
		
		WebDriverWait wait=new WebDriverWait(driver,180);
		
		
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ServiceNow Home Page']")));
		
		System.out.println("Wait completed");
		
		driver.switchTo().frame(0);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Login')]")));
		
		int n=driver.findElements(By.id("user_name")).size();
		
		System.out.println(n);
		
		
		driver.findElement(By.id("user_name")).sendKeys("admin");
		
		driver.findElement(By.id("user_password")).sendKeys("Jan@2020!");
		
		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		
		driver.switchTo().defaultContent();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='filter']")));
		
				
		int buttons = driver.findElements(By.xpath("//button[@id='user_info_dropdown']")).size();
	    
		WebElement ele = driver.findElement(By.xpath("//button[@id='user_info_dropdown']"));
	    //driver.findElement(By.xpath("//button[@id='user_info_dropdown']/following-sibling::ul")).click();
	    
	    System.out.println("No of options is "+ buttons);
	    
	    
	    driver.findElement(By.xpath("//button[@id='user_info_dropdown']")).click();
    	driver.findElement(By.xpath("//button[@id='user_info_dropdown']")).sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);
    	
    	
	    
	    
	    


	}

}
