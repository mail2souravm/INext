package SOURCE_CODE;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
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

	
	//public static WebDriver myWD;
	//public static RemoteWebDriver  RWD;
	
	//public static JavascriptExecutor Javascript;
	public static Capabilities caps;
	public static DesiredCapabilities dCaps;
	
	public static ArrayList al_xl_steps,al_xl_details,al_xl_results,al_xl_screenshot;
	
	public static String start_time_tc,end_time_tc,browser_ver,reasonforfailure;
	public static long start_time,end_time,seconds,hours,minutes,difference;
	
	public static ArrayList<Integer> al_rowno,al_colno;
	public static ArrayList<String> al_ssfilename;
	public static Integer xl_tc_logs_steps_indicator;
	public static String xl_tc_name=null,FolderName,TCName,xpath;
	public static String excel_log_file_directory,excel_log_file_name;
	
	public static ArrayList al_fieldname,al_sectionname,al_relatedlistname;
	public static ArrayList al_tabnames,al_tabnames_var;
	public static ArrayList al_fieldname_fn, al_sectionname_fn,al_relatedlistname_fn;
	public static SFDCAutomationFW sfdc;
	public static String contentofscreenfile,screenname;
	//public static HP_ALM_Integration obj_alm_int;
	public static String path_alm_xl;
	public static Set< String > uniquebuttons;
	public static String ip_address;
	
	public static int xTCRows, xTCCols;
	public static int xlRows, xlCols;
	public static String[][] xlData;
	public static int counterofthreefailsteps;
	public static String isGridEnabled;
	
	
	List<WebElement> allposblefieldelements;
	
	
	//public static String Browser = null;
	//this is third
	//this is second commit test
	//public static String Browser = null;
	public static final ThreadLocal<String> brows = new ThreadLocal<String>();
	public static final ThreadLocal<JavascriptExecutor> Javascript = new ThreadLocal<JavascriptExecutor>();
	
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

/**
 * @author Sourav Mukherjee
 * @Param timeOutInSeconds
 * @return void
 * @throws Exception
 * @Description Waits for the page to reach ready state 
 * @Date Aug 7, 2014
 */
public static void WaitForPageToLoad(int timeOutInSeconds) throws Exception
{ 
	
	String command = "return document.readyState"; 

	try
	{
	for (int i=0; i<timeOutInSeconds; i++)
	{ 
		try
		{
			Thread.sleep(1000L);
		}
		catch (InterruptedException e)
		{
			System.out.println("Unable to load the webpage");				
			
		} 
		
		if (Javascript.get().executeScript(command).toString().equals("complete"))
		{ 
			//System.out.println("Inside WaitForPageToLoad(Success)");
			break; 
		} 
		
		
	} 
	}catch(Exception e) 
	{
		e.printStackTrace();
	}
}


/**
 * @author Sourav Mukherjee
 * @Param 
 * @return void
 * @throws Exception
 * @Description Logs out from sales force 
 * @Date Aug 7, 2014
 */
public static void LogOff() throws Exception
{
	try 
	{
	String xp = "//span[@id='userNavLabel' and normalize-space(@class)='menuButtonLabel']";
	WaitForPageToLoad(15);
	WaitForElement(xp,15);
	myWD.get().findElement(By.xpath(xp)).click();
	
	myWD.get().findElement(By.xpath("//a[contains(@href,'logout.jsp')][1]")).click();
	

	/*
	if (myWD.toString().contains("InternetExplorerDriver"))
	{
		Thread.sleep(1000L);
		//Javascript.executeScript("arguments[0].click();", myWD.findElement(By.xpath("//a[normalize-space(@title)='Logout' and (normalize-space(@role)='menuitem') and contains(normalize-space(text()),'Logout')]")));
	}
	*/
	//myWD.findElement(By.xpath("//a[normalize-space(@title)='Logout' and (normalize-space(@role)='menuitem') and contains(normalize-space(text()),'Logout')]")).click();
	//myWD.findElement(By.xpath("//ul[normalize-space(@class)='zen-options' and normalize-space(@role)='menu'][1]/descendant::li[last()]/descendant::a[1]")).click();
	
	WaitForPageToLoad(30);
	AddToXLLogs("Logging out from salesforce", "Pass");
	System.out.println("Logging out from salesforce");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Unable to Logout from Salesforce.");
		AddToXLLogs("Unable to Logout from Salesforce.", "Fail");
		
	}
}

/**
 * @author Sourav Mukherjee
 * @Param 1. xpath of the element. 2. Waiting Time in Second
 * @return boolean
 * @throws Exception
 * @Description Waits for the specified time until the webelemnt is available to WebDriver
 * @Date Aug 7, 2014
 */
public static boolean WaitForElement(String xpath, long waitingTimeinsec) throws Exception
{
     try {
    	 	//Uncomment WaitForPageLoad() function call when executing the scripts in IE
    	 	//Otherwise comment out
    	 	
    	 	//WaitForPageToLoad(30);
    	 	/*if (myWD.toString().contains("InternetExplorerDriver"))
    	 	{
    	 		if (WebDriverWaitForElement(xpath,waitingTimeinsec)!=null)	
    	 		{
    	 			return true;
    	 		}
    	 		else
    	 		{
    	 			return false;
    	 		}
    	 	}
    	 	else
    	 	{
    		        		
    	 	myWD.manage().timeouts().implicitlyWait(waitingTimeinsec,TimeUnit.SECONDS);
    		List<WebElement> myDynamicElement = myWD.findElements(By.xpath(xpath));
    		if (myDynamicElement.size() > 0)
    		{
    			//System.out.println("Inside WaitForElement(Success):"+myDynamicElement.size());
    			return true;
    		}
    		else
    		{
    			return false;
    		} 
    	 	}*/
    		
    	myWD.get().manage().timeouts().implicitlyWait(waitingTimeinsec,TimeUnit.SECONDS);
 		List<WebElement> myDynamicElement = myWD.get().findElements(By.xpath(xpath));
 		if (myDynamicElement.size() > 0)
 		{
 			System.out.println("Success: WaitForElement->Number of Element present is: "+myDynamicElement.size());
 			return true;
 		}
 		else
 		{
 			System.out.println("Unsuccess: WaitForElement->Number of Element present is: "+myDynamicElement.size());
 			return false;
 		} 
    }
     catch(Exception e)
     {
    	 e.printStackTrace();
         System.out.println("Exception inside WaitForElement:"+xpath);
         return false;
     }
 }
	
/**
 * @author Sourav Mukherjee
 * @Param xpath of the element
 * @return void
 * @throws Exception
 * @Description Moved the mouse pointer to coordinate X+500,Y+500 where X and Y are the coordinates of the element.
 * @Date Aug 7, 2014
 */
public static void MouseMove(String xpathoftheelement) throws Exception  
{
	/*
	Actions action = new Actions(myWD);
	WebElement we = myWD.findElement(By.xpath("//*[contains(normalize-space(text()),'Home')]"));
	action.moveToElement(we).build().perform();
	*/
	
	Point coordinates = myWD.get().findElement(By.xpath(xpathoftheelement)).getLocation();
	Robot robot = new Robot();
	robot.mouseMove(coordinates.getX()+500,coordinates.getY()+400);
	
	
}






/**
 * @author Sourav Mukherjee
 * @Param 1. Enter the Log text message to be sent in the excel logs 2. Pass/Fail
 * @return void
 * @throws Exception
 * @Description Adds the message to excel logs along with Pass/Fail message
 * @Date Aug 7, 2014
 */
public static void AddToXLLogs(String Message,String PassORFail) throws Exception
{
	
	try
	{
	al_xl_details.add(Message);
	al_xl_results.add(PassORFail);
	
	if (PassORFail.equalsIgnoreCase("Fail"))
	{
		counterofthreefailsteps = counterofthreefailsteps + 1;
		System.out.println("Total Step Failed: "+counterofthreefailsteps);
		al_xl_screenshot.add(_GetScreenShot());
					
	}
	else
	{
		al_xl_screenshot.add("NA");
		System.out.println("Total Step Failed: "+counterofthreefailsteps);
	}
	
	
	if (counterofthreefailsteps >= 3) 
	{
		System.out.println("Total Step Failed: "+counterofthreefailsteps);
		
		ExitUponTestScriptStepFails exp = new ExitUponTestScriptStepFails("More than 3 steps fail");
		
		throw exp;
	}
	}catch(Exception e)
	{
		al_xl_screenshot.add("Window not present");
		//counterofthreefailsteps = counterofthreefailsteps + 1;
		
		System.out.println("Unable to capture the screen shot.");
		e.printStackTrace();
		
		if (counterofthreefailsteps >= 3) 
		{
			System.out.println("Total Step Failed: "+counterofthreefailsteps);
			ExitUponTestScriptStepFails exp1 = new ExitUponTestScriptStepFails("More than 3 steps fail");
			
			throw exp1;
		}
	}
}

/**
 * @author Sourav Mukherjee
 * @Param NA
 * @return void
 * @throws Exception
 * @Description Press TAB Key using Robot Class
 * @Date Jan, 2015
 */
public static void PressTABKeyOnWindowAlert() throws Exception
{
	try 
	{
		Robot robot = new Robot(); 
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		AddToXLLogs("Pressed Tab Key on Window Alert.", "Pass");
		System.out.println("Pressed Tab Key on Window Alert.");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		AddToXLLogs("Unable to press Tab key on Window Alert", "Fail");
		System.out.println("Unable to press Tab key on Window Alert");
	}
	
}


/*
 * @Author: Sourav
 * 
 * Whenever you call this function CloseWindow(), You must call SelectWindow() 
 * function after this function call to continue working on the next window, 
 * otherwise the focus to the next workable window will be lost. 
 * 
 */
/**
 * @author Sourav Mukherjee
 * @Param UniqueStringinWindowTitle
 * @return boolean
 * @throws Exception
 * @Description Whenever you call this function CloseWindow(), You must call SelectWindow() function after this function call to continue working on the next window, otherwise the focus to the next workable window will be lost.  
 * @Date Aug 7, 2014
 */
public static boolean CloseWindow(String UniqueStringinWindowTitle) throws Exception
{
	try
	{
		
		String AlreadySelectedWindow = myWD.get().getWindowHandle();   
		System.out.println("AlreadySelectedWindow:"+AlreadySelectedWindow);
		
		Set<String> s = myWD.get().getWindowHandles(); 
        Iterator<String> ite = s.iterator(); 
        while(ite.hasNext()) 
        { 
            String popup = ite.next(); 
            System.out.println("inside closewindow:"+popup);
            if(popup.equalsIgnoreCase(AlreadySelectedWindow))
            { 
                myWD.get().switchTo().window(popup).close(); 
                return true;
            }
            
        }
	System.out.println("Could not find the window with title containing "+UniqueStringinWindowTitle+" while trying to close.");
	return false;
	}catch(Exception e) 
	{
		e.printStackTrace();
		System.out.println("Exception in CloseWindow Function while trying to close the window having title containing  "+UniqueStringinWindowTitle);
		return false;
	}
}


/**
 * @author Sourav Mukherjee
 * @Param WebElement
 * @return void
 * @throws Exception
 * @Description The expected webelemnt is highlights in green color
 * @Date Aug 7, 2014
 */
public static void HighLight(WebElement webe) throws Exception
{
	Javascript.get().executeScript("arguments[0].setAttribute('style', arguments[1]);", webe, "color: green; border: 2px solid green;");
}

/**
 * @author Sourav Mukherjee
 * @Param UniqueStringinWindowTitle
 * @return boolean
 * @throws Exception
 * @Description Selects the window opened by Selenium Webdriver.
 * @Date Aug 7, 2014
 */
public static boolean SelectWindow(String UniqueStringinWindowTitle) throws Exception
{
	
	try
	{
		int c=0;
		String mainwindow = myWD.get().getWindowHandle();   
		System.out.println("mainwindow:"+mainwindow);
		
		Set<String> s = myWD.get().getWindowHandles(); 
        Iterator<String> ite = s.iterator(); 
        while(ite.hasNext()) 
        { 
            String popup = ite.next(); 
            System.out.println("inside SelectWindow:"+popup);
            if(!popup.equalsIgnoreCase(mainwindow))
            { 
                myWD.get().switchTo().window(popup); 
                System.out.println("Window Title is: "+myWD.get().getTitle());
                if(myWD.get().getWindowHandle().contains(popup))
                {
                	//mainwindow = null;
                	//s=null;
                	return true;
                	
                }
                else
                {
                	c = c + 1;
                }
             }
        }
        if (c>0)
        {
        	sfdc.AddToXLLogs("Unable to find the window containing title ("+UniqueStringinWindowTitle+")", "Fail");
        	System.out.println("Unable to find the window containing title ("+UniqueStringinWindowTitle+")");
        	return false;
        }
        else
        {
        	sfdc.AddToXLLogs("Successfully selected the window containing title ("+UniqueStringinWindowTitle+")", "Pass");
        	System.out.println("Successfully selected the window containing title ("+UniqueStringinWindowTitle+")");
        	return true;
        	
        }
	/*	
	Set <String> handles = myWD.getWindowHandles();
	Iterator<String> it = handles.iterator();
	//iterate through your windows
	while (it.hasNext())
	{
		//String parent = it.next();
		String win = it.next();
		System.out.println("win:"+win);
		myWD.switchTo().window(win);
		if (myWD.getTitle().contains(UniqueStringinWindowTitle))
		{
			System.out.println("The title of the Selected window is:"+myWD.getTitle());
			return true;
		}
					
	}
	System.out.println("Could not find the window with title containing "+UniqueStringinWindowTitle);
	return false;
	*/
	}
	catch(Exception e) 
	{
		e.printStackTrace();
		System.out.println("Exception in SelectWindow Function while trying to select the window having title containing  "+UniqueStringinWindowTitle);
		return false;
	}
}

/**
 * @author Sourav Mukherjee
 * @Param 
 * @return Name of the screen shot file name on Success, returns blank value on failure
 * @throws Exception
 * @Description Captures screen shot of the WebDriver window
 * @Date Aug 7, 2014
 */
public static String _GetScreenShot() throws Exception 
{
	try 
	{
	File screenshot = ((TakesScreenshot) myWD.get()).getScreenshotAs(OutputType.FILE);
	if(screenshot.exists())
	{
		String screenshotfilename = TCName+"_"+GetCurrentDateTimeStamp()+".png";
		FileUtils.copyFile(screenshot, new File("test-output\\TestLogs\\"+FolderName+"\\"+screenshotfilename));
		
		return screenshotfilename;	
	}
	else
	{
		return "Window Closed";
	}
	}catch(Exception e) 
	{
		e.printStackTrace();
		System.out.println("Exception:Unable to capture the screen shot");
		return "Window Closed";
	}
}

/**
 * @author Sourav Mukherjee
 * @Param 
 * @return The datevalue in String type as per format ddMMyyHHmmss 
 * @throws Exception
 * @Description 
 * @Date Aug 7, 2014
 */
public static String GetCurrentDateTimeStamp() throws Exception
{
    
   	Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
    return (sdf.format(cal.getTime()));                     
          
}

//******************** Part of OR Library **********************
public MemberOfLink Link(String LinkName) throws Exception
{
	return new MemberOfLink(LinkName);
}
public MemberOfButton Button(String ButtonName) throws Exception
{
	return new MemberOfButton(ButtonName);
}
public MemberOfField Field(String FieldName) throws Exception
{
	return new MemberOfField(FieldName);
}


}
