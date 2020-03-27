package servicenow;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterAndCreateNew {
	
	public WebDriver filterCreateNew(WebDriver driver) throws InterruptedException
	{
		
		WebDriverWait wait=new WebDriverWait(driver,180);
		
		System.out.println("Typing Problem");		
		Thread.sleep(3);		
		driver.findElement(By.xpath("//input[@id='filter']")).click();		
		//typing problem in the filter text box
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("problem",Keys.TAB);
		
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='concourse_application_tree']")));
		//finding Create New element
		
		System.out.println("Element found");
		
			
		String create_new=null;
		//String create_new = "//li[14]/ul/li/div/div/a/div/div[contains(text(),'Create New')]";
		
		WebElement drv = driver.findElement(By.xpath("//ul[@id='concourse_application_tree']"));
		
		int n=drv.findElements(By.tagName("li")).size();
		
		System.out.println("No of LI is"+n);
		
		for(int i=1;i<=n;i++)
		{
			create_new="//li["+i+"]/ul/li/div/div/a/div/div[contains(text(),'Create New')]";
			
			int m = drv.findElements(By.xpath(create_new)).size();
			
			//System.out.println("LI at "+i+" is "+ m + " Count");
			
			if(m==1)
			{
				//System.out.println(create_new);
				
				WebElement el = drv.findElement(By.xpath(create_new));
				if(isClickable(el, driver))
				{
					drv.findElement(By.xpath(create_new)).click();
					break;
				}				
				
			}
		}
		
		
		return driver;
	}
	
	
	public static boolean isClickable(WebElement el, WebDriver driver) 
	{
		try{
			WebDriverWait wait = new WebDriverWait(driver, 6);
			wait.until(ExpectedConditions.elementToBeClickable(el));
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

}
