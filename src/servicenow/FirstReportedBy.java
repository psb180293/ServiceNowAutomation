package servicenow;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FirstReportedBy {

	public WebDriver firstRep(WebDriver driver) throws InterruptedException
	{
		
		WebElement lkp=driver.findElement(By.xpath("//button[@name='lookup.problem.first_reported_by_task']/span"));
		
		Thread.sleep(5000);		
		lkp.click();
		
		Thread.sleep(5000);	
		
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
	    
	    
	    String chng_number = driver.findElement(By.xpath("//table[@id='task_table']/tbody/tr[2]/td[3]/a")).getText();
	    
	    System.out.println("First Reported by change Number is : "+chng_number);
	    
	    driver.findElement(By.xpath("//table[@id='task_table']/tbody/tr[2]/td[3]/a")).click();
	    
	    driver.switchTo().window(p_window);
	    
	    driver.switchTo().defaultContent();
	    
		
		return driver;
		
	}
	
	
	
}
