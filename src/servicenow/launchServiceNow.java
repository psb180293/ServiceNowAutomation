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

public class launchServiceNow {
	
	
	
	
	public static void startServiceNow(String url,String username,String password,String chromepath) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver",chromepath);		
		WebDriver driver = new ChromeDriver();		
		driver.get(url);		
		driver.manage().window().maximize();		
		WebDriverWait wait=new WebDriverWait(driver,180);			
		driver.switchTo().frame(0);
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Login')]")));
		
		//Step 1 to 6 :::: Log In to Service Now//	
		Login lg = new Login();		
		driver = lg.loginServiceNow(driver,username,password);		
		driver.switchTo().defaultContent();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='filter']")));
		
		
		//step 7 :::: Type in Filter and Click Create Now
		FilterAndCreateNew fn= new FilterAndCreateNew();
		driver= fn.filterCreateNew(driver);	
		
		
		//Step 8 :::: Capture problem Number
		Problem pn = new Problem();
		ProblemNumber problem = pn.problemNumber(driver);		
		String problem_number=problem.problem_number;
		driver=problem.driver;		
		System.out.println("Problem Number is "+problem_number);
		
		
		wait.until(ExpectedConditions.titleContains("Create"));
		
		
		//Step 9 :::: Choose first Reported By		
		FirstReportedBy frb= new FirstReportedBy();
		driver=frb.firstRep(driver);		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='gsft_main']")));
	    
	    
	    //Step 10 ::: Select category Option
		Set<String>whndl = driver.getWindowHandles();
		int window_handles = whndl.size();
		System.out.println("Number of open windows is "+window_handles);		
	    WebElement category = driver.findElement(By.xpath("//select[@id='problem.category']"));	    
	    int opt=category.findElements(By.tagName("option")).size();
	    category.findElements(By.tagName("option")).get(opt-1).click();
	    
	    
	    Thread.sleep(2000);
		
	    //Step 11 ::: Select SubCategory Option
	    ChooseSubCat csc= new ChooseSubCat();
	    driver=csc.chooseSubCategory(driver);
	    
	    
	    Thread.sleep(2000);
	    
	    //Step 12 ::: Select Service Lookup
	    ServiceLookup sl= new ServiceLookup();
	    driver=sl.servLookup(driver);
	    Thread.sleep(5000);
	    
	    	    
	    //Step 13 ::: Configuration Item
	    ConfigurationItem ci= new ConfigurationItem();
	    driver=ci.configItem(driver);
	    Thread.sleep(5000);
	    
	    
	    //Step 14 ::: Problem Statement typing	    
	    driver.switchTo().defaultContent();	    
	    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='gsft_main']")));	    
	    System.out.println("Frame switched");
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());	    
	    String problem_statement= "TESTLEAF CONTEST "+timestamp;	    
	    driver.findElement(By.id("problem.short_description")).sendKeys(problem_statement);
	    
	    
	    
	    
	    //Step 15 ::: Confirm State
	    WebElement st= driver.findElement(By.id("sys_readonly.problem.state"));	    
	    String state = st.findElements(By.tagName("option")).get(0).getText();	    
	    System.out.println("State is "+state.equalsIgnoreCase("New"));
	    
	    
	    //Step 16 :::Select Impact and Urgency
	    String impact= "problem.impact";
	    String urgency="problem.urgency";
	    int index=1;
	    ImpactUrgencyDDN iud= new ImpactUrgencyDDN();
	    driver=iud.ImpactUrgency(impact, index, driver);	    
	    Thread.sleep(2000);
	    driver=iud.ImpactUrgency(urgency, index, driver);
	    
	  	    
	    Thread.sleep(3000);
	    
	    
	    //Step 17 ::: Type and choose assigned to  
	    driver.findElement(By.xpath("//input[@id='sys_display.problem.assigned_to']")).click();
	    driver.findElement(By.xpath("//input[@id='sys_display.problem.assigned_to']")).sendKeys("Prob");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//input[@id='sys_display.problem.assigned_to']")).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
	    
	    
	    Thread.sleep(2000);
	    
	    
	    //Step 18 ::: Submit the problem
	    driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
	    
	        
	    wait.until(ExpectedConditions.titleIs("Problems | ServiceNow"));
	    
	    
	    //Step 19 ::: Search and verify the problem number
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
	    
	    
	    
	    //Step 20 ::: Logout and close the browser
	    Logout off= new Logout();
	    driver=off.logout(driver); 
	    wait.until(ExpectedConditions.titleIs("ServiceNow"));
	    
	    alertgenerator(driver);
	    
	    driver.quit();
	    
	}
	
	
	
	public static void alertgenerator(WebDriver driver) throws InterruptedException
	{
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
	    javascript.executeScript("alert('All Steps completed successfully!!!');");
	    Thread.sleep(3000);
	    driver.switchTo().alert().accept();
	    
	}
	
}
