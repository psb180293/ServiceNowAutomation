package servicenow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

final class ProblemNumber {

	
	public String problem_number;
	public WebDriver driver;
	
	public ProblemNumber(String problem_number,WebDriver driver)
	{
		this.problem_number=problem_number;
		this.driver=driver;
	}	
	
}


class Problem
{
	public ProblemNumber problemNumber(WebDriver driver)
	
	{
		WebDriverWait wait=new WebDriverWait(driver,180);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='gsft_main']")));
		
		//WebElement el=driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='sys_readonly.problem.number']")));
		
		
		String problem_number= driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']")).getAttribute("value");
		
		WebElement el=driver.findElement(By.xpath("//input[@id='sys_readonly.problem.number']"));
		
		if (el.getAttribute("readonly") !=null)
		{
			System.out.println("Field is Read Only");
		}
		
		return new ProblemNumber(problem_number,driver);
		
	}
}