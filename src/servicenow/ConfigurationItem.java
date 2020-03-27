package servicenow;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfigurationItem {

	public WebDriver configItem(WebDriver driver) throws InterruptedException
	{
		System.out.println("Starting Configuration item.....");
	    
	    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='gsft_main']")));
	    
	    System.out.println("Frame switched");
	    
	    driver=configurationItem(driver);
		
		
		return driver;
	}
	
	
	public static WebDriver configurationItem(WebDriver driver) throws InterruptedException
	{
		
		WebElement lkp=driver.findElement(By.xpath("//button[@name='lookup.problem.cmdb_ci']/span"));
		
		Thread.sleep(10000);		
		lkp.click();
		
		Thread.sleep(10000);
		
		//String parent_window= driver.getTitle();
		String child_window=null;
		
		String p_window = driver.getWindowHandle(); 
		//System.out.println("Current window is "+p_window);
				
		Set<String>handles=driver.getWindowHandles();
	    Iterator<String>itr=handles.iterator();
	    while(itr.hasNext())
	      {
	         String new_window=itr.next();
	         if(!p_window.equals(new_window)) //compare if current window is not parent
	                driver.switchTo().window(new_window); 
	         		child_window=driver.getTitle();
	                System.out.println(child_window);
	                driver.manage().window().maximize();
	                Thread.sleep(5000);
	      }
	    
	    String total_rows=driver.findElement(By.xpath("//table[@id='cmdb_ci_table']")).getAttribute("total_rows");
	    System.out.println(total_rows);
	    
	    int total_row=Integer.parseInt(total_rows);
	    
	    
	    int randomNum = ThreadLocalRandom.current().nextInt(100, total_row + 1);
	    

	    String random= String.valueOf(randomNum);
	    
	    System.out.println("random number generated is "+random);

	    
	    
	    driver.findElement(By.xpath("//input[@aria-label='Skip to row']")).clear();
	    driver.findElement(By.xpath("//input[@aria-label='Skip to row']")).sendKeys(random,Keys.ENTER);
	    
	    Thread.sleep(5000);
	    
	    driver.findElement(By.xpath("//table[@id='cmdb_ci_table']/tbody/tr[1]/td[3]/a")).click();
	    
	        
		driver.switchTo().window(p_window);
		
		return driver;
	}
	
}
