package servicenow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	
	public WebDriver loginServiceNow(WebDriver driver,String username, String password)
	{
		
driver.findElement(By.id("user_name")).sendKeys(username);
		
		driver.findElement(By.id("user_password")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		
		return driver;
		
	}

}
