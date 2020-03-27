package servicenow;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ServiceLookup {

	public WebDriver servLookup(WebDriver driver) throws InterruptedException
	{
		
		driver=serviceLookup(driver);	    
	    driver.switchTo().defaultContent();		
		return driver;
	}
	
	
	public static WebDriver serviceLookup(WebDriver driver) throws InterruptedException
	{
		
		WebElement lkp=driver.findElement(By.xpath("//button[@name='lookup.problem.business_service']/span"));
		
		Thread.sleep(10000);		
		lkp.click();
		
		Thread.sleep(10000);	
		
		String p_window = driver.getWindowHandle(); 
		//System.out.println("Current window is "+p_window);
				
		Set<String>handles=driver.getWindowHandles();
	    Iterator<String>itr=handles.iterator();
	    while(itr.hasNext())
	      {
	         String new_window=itr.next();
	         if(!p_window.equals(new_window)) //compare if current window is not parent
	                 driver.switchTo().window(new_window); 
	                 System.out.println(driver.getTitle());
	                 driver.manage().window().maximize();
	                 Thread.sleep(3000);
	      }
	    
	    
	    WebElement service = driver.findElement(By.xpath("//table[@id='cmdb_ci_service_table']/tbody"));
	    
	    int n = service.findElements(By.tagName("tr")).size();
	    
	    System.out.println("No of TR tag in service is "+n);
	    
	    service.findElement(By.xpath("//table[@id='cmdb_ci_service_table']/tbody/tr["+n+"]/td[3]/a")).click();
	    
	    driver.switchTo().window(p_window);
	    
	    return driver;
	}
	
	
}
