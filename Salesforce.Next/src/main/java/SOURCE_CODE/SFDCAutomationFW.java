package SOURCE_CODE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SFDCAutomationFW {

	//public static String Browser = null;
	//this is third
	//this is second commit test
	//public static String Browser = null;
	public static final ThreadLocal<String> brows = new ThreadLocal<String>();
	
	
	public static final ThreadLocal<WebDriver> myWD = new ThreadLocal<WebDriver>(){
        @Override
        protected WebDriver initialValue()
        {
        	try {
        	if (brows.get().equalsIgnoreCase("chrome"))
    		{
    			System.setProperty("webdriver.chrome.driver","D:\\WS_LI_UI_2017\\OTHERS\\chromedriver.exe");
    			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    			return (new ChromeDriver());
    						
    		}
    		else if(brows.get().equalsIgnoreCase("ff") ||brows.get().equalsIgnoreCase("firefox"))
    		{
    			
    			System.setProperty("webdriver.gecko.driver","D:\\WS_LI_UI_2017\\OTHERS\\geckodriver.exe");
    			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    			return (new FirefoxDriver());
    		   
    		}
    		else if(brows.get().equalsIgnoreCase("Edge"))
    		{
    			System.setProperty("webdriver.edge.driver","D:\\WS_LI_UI_2017\\OTHERS\\MicrosoftWebDriver.exe");
    			DesiredCapabilities capabilities = DesiredCapabilities.edge();
    			return (new EdgeDriver());
    		}
    		else if(brows.get().equalsIgnoreCase("ie"))
    		{
    			System.setProperty("webdriver.ie.driver", "D:\\WS_LI_UI_2017\\OTHERS\\IEDriverServer.exe");
    			//DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    			return (new InternetExplorerDriver());
    		}
        	return null;
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        		return null;
        	}
        	    		
        }
	};
	
	public boolean OpenURL(String[] args,String URL,String browser) throws Exception
	{
		try 
		{
			if ((args != null))
			{
				System.out.println("args[0] is: "+args[0]);
				//browser = args[0];
				
			}
						
			brows.set(browser);
		
			myWD.get().navigate().to(URL);
			return true;
		
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	

	public static boolean LoginToSFDC(String un,String pw) throws Exception
	{
		try
		{
			
		Thread.sleep(2000L);
		String xpath_UserName = "//input[normalize-space(@type)='email' and normalize-space(@id)='username'][1]";
		String xpath_Password = "//input[normalize-space(@type)='password' and normalize-space(@id)='password'][1]";
		String xpath_LoginButton = "//input[contains(@class,'button') and @type='submit'][1]";
		myWD.get().findElement(By.xpath(xpath_UserName)).clear();
		myWD.get().findElement(By.xpath(xpath_UserName)).sendKeys(un);
	 	myWD.get().findElement(By.xpath(xpath_Password)).clear();
		myWD.get().findElement(By.xpath(xpath_Password)).sendKeys(pw);
		myWD.get().findElement(By.xpath(xpath_LoginButton)).click();
			
		
		
		Thread.sleep(5000L);		
			
		
		myWD.get().findElement(By.xpath("//a[text()='No Thanks'][1]")).click();
		Thread.sleep(3000L);
		myWD.get().findElement(By.xpath("//label[@for='lex-checkbox-7'][1]/span[1]")).click();
		Thread.sleep(2000L);
		myWD.get().findElement(By.xpath("//input[@title='Send to Salesforce'][1]")).click();
	
		WebDriverWaitForElement("//a[contains(normalize-space(text()),'Home')]", 30);
		return true;
		}catch(Exception e)
		{        	
			e.printStackTrace();
			return false;
		}
		
}
	
public static WebElement WebDriverWaitForElement(String xpath, long waitingTimeinsec) throws Exception
{
	WebElement element=null;
    try {
    	 	WebDriverWait wait = new WebDriverWait(myWD.get(), waitingTimeinsec);
    	 	element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    	 	return element;
       	 }
     	catch(Exception e)
     	{
     		e.printStackTrace();
     		return element;
    	 
     	}
 }
	
}
