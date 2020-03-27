package servicenow;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ImpactUrgencyDDN {
	
	public WebDriver ImpactUrgency(String impact,int index,WebDriver driver)
	{
		
		driver = selectDropDown_JS(driver,impact,index);
		
		return driver;
	}
	
	
	public static WebDriver selectDropDown_JS(WebDriver driver, String id,int index)
	{	
		
		JavascriptExecutor jse= (JavascriptExecutor)driver;
		
		jse.executeScript("return document.getElementById('"+id+"').selectedIndex = '" + index + "';");
		
		return driver;
		
	}

}
