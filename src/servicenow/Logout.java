package servicenow;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Logout {
	
	public WebDriver logout(WebDriver driver)
	{
		//driver.findElement(By.xpath("//button[@id='user_info_dropdown']/following-sibling::ul")).click();
	    
	    	    
	    driver.findElement(By.xpath("//button[@id='user_info_dropdown']")).click();
    	driver.findElement(By.xpath("//button[@id='user_info_dropdown']")).sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);
    	
		return driver;
		
		
	}

}
