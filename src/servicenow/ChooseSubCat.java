package servicenow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChooseSubCat {
	
	public WebDriver chooseSubCategory(WebDriver driver) throws InterruptedException
	{
		
		WebElement subcategory = driver.findElement(By.xpath("//select[@id='problem.subcategory']"));
	    subcategory = selectDropDown(subcategory);
	    return driver;
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
	
}
