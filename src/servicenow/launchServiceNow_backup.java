package servicenow;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
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

public class launchServiceNow_backup {
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver_win32\\chromedriver.exe");		
		WebDriver driver = new ChromeDriver();		
		String url= "https://dev62656.service-now.com/";		
		driver.get(url);		
		driver.manage().window().maximize();		
		WebDriverWait wait=new WebDriverWait(driver,180);			
		driver.switchTo().frame(0);
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Login')]")));
		
		//Log In to Service Now//		
		Login lg = new Login();		
		driver = lg.loginServiceNow(driver,"user","password");		
		driver.switchTo().defaultContent();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='filter']")));
		
		
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
		
		
		
		
		//Step 8
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='gsft_main']")));
		
		//WebElement el=driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='sys_readonly.problem.number']")));
		
		
		String problem_number= driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']")).getAttribute("value");
		
		
					
		System.out.println("Problem number is :  "+problem_number);
		
		WebElement el=driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']"));
		
		if (el.getAttribute("readonly") !=null)
		{
			System.out.println("Field is Read Only");
		}
		
		wait.until(ExpectedConditions.titleContains("Create"));
		
		WebElement lkp=driver.findElement(By.xpath("//button[@name='lookup.problem.first_reported_by_task']/span"));
		
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
	    
	    
	    String chng_number = driver.findElement(By.xpath("//table[@id='task_table']/tbody/tr[2]/td[3]/a")).getText();
	    
	    System.out.println("Change Number is : "+chng_number);
	    
	    driver.findElement(By.xpath("//table[@id='task_table']/tbody/tr[2]/td[3]/a")).click();
	    
	    driver.switchTo().window(p_window);
	    
	    driver.switchTo().defaultContent();
	    
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='gsft_main']")));
	    
	    
	    
	    WebElement category = driver.findElement(By.xpath("//select[@id='problem.category']"));
	    
	    category = selectDropDown(category);
	    Thread.sleep(2000);
		
	    
	    
	    WebElement subcategory = driver.findElement(By.xpath("//select[@id='problem.subcategory']"));
	    subcategory = selectDropDown(subcategory);
	    Thread.sleep(2000);
	    
	    
	    //SERVICE LOOKUP
	    
	    driver=serviceLookup(driver);
	    
	    driver.switchTo().defaultContent();
	    
	    Thread.sleep(5000);
	    
	    	    
	    //CONFIGURATION ITEM
	    
	    System.out.println("Starting Configuration item.....");
	    
	    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='gsft_main']")));
	    
	    System.out.println("Frame switched");
	    
	    driver=configurationItem(driver);
	    
	    //Problem Statement typing
	    
	    driver.switchTo().defaultContent();
	    
	    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='gsft_main']")));
	    
	    System.out.println("Frame switched");
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    
	    String problem_statement= "TESTLEAF CONTEST "+timestamp;
	    
	    driver.findElement(By.id("problem.short_description")).sendKeys(problem_statement);
	    
	    WebElement st= driver.findElement(By.id("sys_readonly.problem.state"));
	    
	    String state = st.findElements(By.tagName("option")).get(0).getText();
	    
	    System.out.println("State is "+state.equalsIgnoreCase("New"));
	    
	    
	    //WebElement impact = driver.findElement(By.xpath("//select[@id='problem.impact']/option[2]"));	
	    String impact ="problem.impact";
	    int index=1;
	    driver = selectDropDown_JS(driver,impact,index);
	    Thread.sleep(2000);
	    
	    //WebElement urgency = driver.findElement(By.xpath("//select[@id='problem.urgency']/option[2]"));
	    String urgency="problem.urgency";
	    index=1;
	    driver = selectDropDown_JS(driver,urgency,index);
	    Thread.sleep(3000);
	    
	    
	      
	    driver.findElement(By.xpath("//input[@id='sys_display.problem.assigned_to']")).click();
	    driver.findElement(By.xpath("//input[@id='sys_display.problem.assigned_to']")).sendKeys("Prob");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@id='sys_display.problem.assigned_to']")).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
	    
	    Thread.sleep(2000);
	    
	    driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
	    
	    Thread.sleep(2000);
	    
	    wait.until(ExpectedConditions.titleIs("Problems | ServiceNow"));
	    
	    System.out.println(driver.getTitle());
	    driver.findElement(By.xpath("//input[@placeholder='Search' and contains(@id,'text')]")).click();
	    driver.findElement(By.xpath("//input[@placeholder='Search' and contains(@id,'text')]")).sendKeys(problem_number,Keys.ENTER);
	    
	    
	    Thread.sleep(6000);
	    
	    String validation_problem=driver.findElement(By.xpath("//table[@id='problem_table']/tbody/tr[1]/td[3]")).getText();
	    String validation_time=driver.findElement(By.xpath("//table[@id='problem_table']/tbody/tr[1]/td[4]")).getText();
	    
	    if(validation_problem.equalsIgnoreCase(problem_number) && validation_time.equalsIgnoreCase(problem_statement))
	    {
	    	System.out.println("Validation Completed");
	    	System.out.println("Problem number is : "+problem_number+" and Problem Statement is "+problem_statement);
	    }
	    
	    driver.switchTo().defaultContent();
	    
	    
	    
	   driver=logout(driver);
	    
	   wait.until(ExpectedConditions.titleIs("ServiceNow"));
	   
	   driver.quit();
	    
	    
	    
	}
	
	
	
	//function to check if element is interactable or not
	
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

	
	public static WebElement selectDropDown(WebElement ddn)
	{
		int n=ddn.findElements(By.tagName("option")).size();
	    
	    System.out.println("No of options in this dropdown is "+(n-1));
	    
	    int len=0;
	    int max_pos=0;
	    
	    for(int i=0;i<n;i++)
	    {
	    	String drop= ddn.findElements(By.tagName("option")).get(i).getText();
	    	System.out.println("Value is "+drop);
	    	
	    	if((!drop.contains("None"))&&(drop.length()>len))
	    	{
	    		len=drop.length();
	    		max_pos=i;
	    		
	    	}
	    }
	    
	    String select=ddn.findElements(By.tagName("option")).get(max_pos).getText();
	    
	    System.out.println("Longest value is "+select+" at position "+max_pos);
	    
	    ddn.findElements(By.tagName("option")).get(max_pos).click();
	    
	    return ddn;
	    
	    
	}
	
	public static WebDriver selectDropDown_JS(WebDriver driver, String id,int index)
	{	
		
		//WebElement ddn = driver.findElement(By.xpath(xpath));
		
		//((JavascriptExecutor) driver).executeScript("return document.getElementById('problem.impact').selectedIndex = '" + index + "';");
		
		JavascriptExecutor jse= (JavascriptExecutor)driver;
		
		jse.executeScript("return document.getElementById('"+id+"').selectedIndex = '" + index + "';");
		
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
	
	
	public static WebDriver logout(WebDriver driver)
	{
		
		WebElement ele = driver.findElement(By.xpath("//button[@id='user_info_dropdown']"));
	    //driver.findElement(By.xpath("//button[@id='user_info_dropdown']/following-sibling::ul")).click();
	    
	    	    
	    driver.findElement(By.xpath("//button[@id='user_info_dropdown']")).click();
    	driver.findElement(By.xpath("//button[@id='user_info_dropdown']")).sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);
    	
		return driver;
		
	}

}
