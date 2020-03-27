package servicenow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	
	public WebDriver loginServiceNow(WebDriver driver)
	{
		
driver.findElement(By.id("user_name")).sendKeys("admin");
		
		driver.findElement(By.id("user_password")).sendKeys("Jan@2020!");
		
		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		
		return driver;
		
	}

}
