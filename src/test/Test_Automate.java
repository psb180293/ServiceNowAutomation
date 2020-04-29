package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test_Automate {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		
		String time_sheet="04/26/2020"; //mm/dd/yyyy format
		
		System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://i3lmobile.itcinfotech.com");
		driver.manage().window().maximize();
		
		try {
		driver.findElement(By.id("userid")).sendKeys("33421");
		}
		catch (Exception e) {
			e.getMessage();
			System.exit(0);
		}
		
		try {
		driver.findElement(By.id("pwd")).sendKeys("Apr@2020!");
		}
		catch (Exception e) {
			e.getMessage();
		}
		
		
		
		driver.findElement(By.name("Submit")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Time Sheet')]")));
		
		driver.findElement(By.xpath("//*[contains(text(),'Time Sheet')]")).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Thread.sleep(2000);
		
		
		driver.switchTo().frame("ptifrmtgtframe");
		int a = driver.findElements(By.xpath("//a[@title='Enter project and personal time and submit a report for approval.']")).size();
		System.out.println(a);
		
		driver.findElements(By.xpath("//a[@title='Enter project and personal time and submit a report for approval.']")).get(0).click();
		
		Thread.sleep(2000);
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("ptifrmtgtframe");
		
    	
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@name='EX_TIME_ADD_VW_PERIOD_END_DT']")).click();
		driver.findElement(By.xpath("//input[@name='EX_TIME_ADD_VW_PERIOD_END_DT']")).clear();			
		driver.findElement(By.xpath("//input[@name='EX_TIME_ADD_VW_PERIOD_END_DT']")).sendKeys(time_sheet);
		
		driver.findElement(By.xpath("//input[@value='Add']")).click();
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("ptifrmtgtframe");
		
		
		driver.findElement(By.xpath("//a[@name='EX_TIME_HDR_WRK_COPY_TIME_RPT']")).click();
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("ptifrmtgtframe");
		
		

		
		a=driver.findElements(By.xpath("//*[@id='trEX_TIME_CPY_VW$0_row1']/td[1]")).size();
		System.out.println("Select is "+a);
		
		driver.findElement(By.xpath("//*[@id='trEX_TIME_CPY_VW$0_row1']/td[1]")).click();
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("ptifrmtgtframe");
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("ptifrmtgtframe");
		
		//Thread.sleep(2000);
		
		for (int i=1;i<6;i++) {
			String derived_xpath="//tr[@id='trEX_TIME_DTL$0_row1']/td["+(i+1)+"]/div/input";
			System.out.println(derived_xpath);
			driver.findElement(By.xpath(derived_xpath)).click();
			driver.findElement(By.xpath(derived_xpath)).sendKeys("7.2");
			
		}
		
		
		for (int i=1;i<6;i++) {
			
			String derived_xpath="//tr[@id='trEX_TIME_DTL$0_row2']/td["+(i+1)+"]/div/input";
			driver.findElement(By.xpath(derived_xpath)).click();
			driver.findElement(By.xpath(derived_xpath)).sendKeys("1.8");
			
		}
		
		
		driver.findElement(By.xpath("//input[@value='Update Totals']")).click();
		
		Thread.sleep(5000);
		
		String total = driver.findElement(By.xpath("//span[@id='EX_TM_DT_DLY_WK_DTL_TOTAL']")).getText();
		
		String expected="45.00";
		
		System.out.println("total: "+total);
		
		if(total.equals(expected)) {
			
			driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		}	
		
		driver.switchTo().defaultContent();
		
		//driver.switchTo().activeElement();
		System.out.println("Current window is : "+driver.getTitle());
		
		int frm0 = driver.findElements(By.id("ptModFrame_0")).size();
		int frm1 = driver.findElements(By.id("ptModFrame_1")).size();
		
		if (frm0==0) {
			driver.switchTo().frame("ptModFrame_1");
		}
		else {
			driver.switchTo().frame("ptModFrame_0");
		}
		//int handles=driver.getWindowHandles().size();
		//System.out.println(handles);
		
		WebElement ok=driver.findElement(By.xpath("//input[@value='OK']"));
		//System.out.println(driver.getPageSource());
		
		ok.click();
		
		System.out.println("Completed.....");
		
		
	}

}
