package SOURCE_CODE;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;



public class SFDCAutomationFW {

	
	//public static WebDriver myWD;
	//public static RemoteWebDriver  RWD;
	
	//public static JavascriptExecutor Javascript;
	public static Capabilities caps;
	public static DesiredCapabilities dCaps;
	public static String ProjectHomeDir = "";
	
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
	public static boolean flag_driverinstance_chrome = false;
	public static boolean flag_driverinstance_ff = false;
	//public static int count_driverinstance_ie = 0;
	//public static int count_driverinstance_edge = 0;
	
	List<WebElement> allposblefieldelements;
	public static final ThreadLocal<Integer> thrd_id = new ThreadLocal<Integer>();
	
	//public static String Browser = null;
	//this is third
	//this is second commit test
	//public static String Browser = null;
	public static final ThreadLocal<String> brows = new ThreadLocal<String>();
	public static final ThreadLocal<String> grid = new ThreadLocal<String>();
	public static final ThreadLocal<String> platform = new ThreadLocal<String>();
	public static final ThreadLocal<String> udid = new ThreadLocal<String>();
	public static final ThreadLocal<String> devicename = new ThreadLocal<String>();
	public static final ThreadLocal<String> version = new ThreadLocal<String>();
	public static final ThreadLocal<String> appium_host_url = new ThreadLocal<String>();
	
	
	public static AppiumDriver appdr;
	
	
	//public static final ThreadLocal<JavascriptExecutor> Javascript = new ThreadLocal<JavascriptExecutor>();
	public static final ThreadLocal<JavascriptExecutor> Javascript = new ThreadLocal<JavascriptExecutor>(){
        @Override
        protected JavascriptExecutor initialValue()
        {
        	return new JavascriptExecutor() {
				
				@Override
				public Object executeScript(String script, Object... args) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Object executeAsyncScript(String script, Object... args) {
					// TODO Auto-generated method stub
					return null;
				}
			};
        }
    };
	
  /*
	public static final ThreadLocal<AppiumDriver> appdr = new ThreadLocal<AppiumDriver>() {
		@Override
		 protected AppiumDriver initialValue()
		 {
			try {
			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
			capabilities.setCapability("appPackage", "com.android.chrome");
			capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
			
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
			
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "4f9d96c0");
			capabilities.setCapability(MobileCapabilityType.VERSION, "6.0.1");
			
			return (new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities));
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
	};
			
	*/		
	public static final ThreadLocal<RemoteWebDriver> myWD = new ThreadLocal<RemoteWebDriver>(){
        @Override
        protected RemoteWebDriver initialValue()
        {
        	try {
        	
        	ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/");
        		
        	if(grid.get().equalsIgnoreCase("no") && platform.get().equalsIgnoreCase("Windows") )
        	{
        		if (brows.get().equalsIgnoreCase("chrome"))
        		{
            		//ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/"); 
            		
            		System.setProperty("webdriver.chrome.driver",ProjectHomeDir + "/src/test/resources/chromedriver.exe");
        			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        			
        			System.out.println("creating chromedriver instance");
        			RemoteWebDriver rwd = new ChromeDriver();
        			Javascript.set(rwd);
        			
        			return (rwd);
        						
        		}
        		else if((brows.get().equalsIgnoreCase("ff") ||brows.get().equalsIgnoreCase("firefox")))
        		{
        			//ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/");
        			System.setProperty("webdriver.gecko.driver",ProjectHomeDir + "/src/test/resources/geckodriver.exe");
        			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        			System.out.println("creating ffdriver instance");
        			RemoteWebDriver rwd = new FirefoxDriver();
        			Javascript.set(rwd);
        			
        			return (rwd);
        		   
        		}
        		else if(brows.get().equalsIgnoreCase("Edge"))
        		{
        			//ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/");
        			System.setProperty("webdriver.edge.driver",ProjectHomeDir + "/src/test/resources/MicrosoftWebDriver.exe");
        			DesiredCapabilities capabilities = DesiredCapabilities.edge();
        			
        			RemoteWebDriver rwd = new EdgeDriver();
        			Javascript.set(rwd);
        			
        			return (rwd);
        		}
        		else if(brows.get().equalsIgnoreCase("ie"))
        		{
        			//ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/");
        			System.setProperty("webdriver.ie.driver", ProjectHomeDir + "/src/test/resources/IEDriverServer.exe");
        			//DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        			System.out.println("creating iedriver instance");
        			RemoteWebDriver rwd = new InternetExplorerDriver();
        			Javascript.set(rwd);
        			System.out.println("created ie instance");
        			return (rwd);
        		}
        		
        	}
        		
        	// ************************************* Grid ***********************************//
        	
        	else if(grid.get().equalsIgnoreCase("yes")  && platform.get().equalsIgnoreCase("windows") )
        	{
        		if (brows.get().equalsIgnoreCase("chrome"))
        		{
            		
            		//ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/"); 
            		//System.setProperty("webdriver.chrome.driver",ProjectHomeDir + "/src/test/resources/chromedriver.exe");
            		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            		capabilities.setCapability("chrome.binary", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            		capabilities.setBrowserName("chrome");
        			capabilities.setPlatform(Platform.WINDOWS);    			    			
            		
            		RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            		
        			Javascript.set(rwd);
        			
        			return (rwd);
        						
        		}
        		else if((brows.get().equalsIgnoreCase("ff") || brows.get().equalsIgnoreCase("firefox")))
        		{
        			//ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/"); 
            		
            		//System.setProperty("webdriver.gecko.driver",ProjectHomeDir + "/src/test/resources/geckodriver.exe");
        			//FirefoxOptions ffoptions = new FirefoxOptions();
        			//ffoptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        			
        			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        			capabilities.setCapability("marionette", true);
        			capabilities.setBrowserName("firefox");
        			capabilities.setPlatform(Platform.WINDOWS);
        			
        			//capabilities.setCapability("moz:FirefoxOptions", ffoptions);
        			//capabilities.setBrowserName("firefox");
        			//capabilities.setCapability("marionette", false);
        			//capabilities.setPlatform(Platform.WIN10);    			    			
        			RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        			Javascript.set(rwd);
        			
        			return (rwd);
        		   
        		}
        		else if(brows.get().equalsIgnoreCase("Edge"))
        		{
        			//ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/"); 
        			//System.setProperty("webdriver.edge.driver",ProjectHomeDir + "/src/test/resources/MicrosoftWebDriver.exe");
        			
            		
        			DesiredCapabilities capabilities = DesiredCapabilities.edge();
        			capabilities.setPlatform(Platform.WINDOWS);    			    			
        			RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        			Javascript.set(rwd);
        			
        			return (rwd);
        		}
            	
        		else if(brows.get().equalsIgnoreCase("ie") || brows.get().equalsIgnoreCase("internetexplorer"))
        		{
        			 
            		
        			//System.setProperty("webdriver.ie.driver", ProjectHomeDir + "/src/test/resources/IEDriverServer.exe");
        			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        			capabilities.setPlatform(Platform.WINDOWS);    			    
        			//capabilities.setBrowserName("iexplore");
        			//capabilities.setVersion("11");
        			RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        			rwd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        			rwd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        			Javascript.set(rwd);
        			
        			return (rwd);
        		}        		
        	}
        	else if(grid.get().equalsIgnoreCase("yes")  && platform.get().equalsIgnoreCase("android") )
        	{
        		DesiredCapabilities capabilities = DesiredCapabilities.android();
    			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
    			
    			///ChromeOptions options=new ChromeOptions();
    		    //options.setExperimentalOption("androidPackage", "com.android.chrome");
    		    //capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    		    
    			//capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
    			capabilities.setCapability("appPackage", "com.android.chrome");
    			capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
    			
    			//capabilities.setCapability("appPackage", "com.android.chrome");
    		    //capabilities.setCapability("appActivity","com.google.android.apps.chrome.ChromeTabbedActivity");
    		    
    			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
    			
    			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, devicename.get()); //
    			//capabilities.setCapability(MobileCapabilityType.UDID, udid.get());
    			capabilities.setCapability(MobileCapabilityType.VERSION, version.get());
    			//capabilities.setCapability("newCommandTimeout", 600);
    			  			
    			//capabilities.setPlatform(Platform.WINDOWS);    			    
    			//capabilities.setBrowserName("iexplore");
    			//capabilities.setVersion("11");
    			//RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); //Working code
    			//RemoteWebDriver rwd = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); //Working code
    			//manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    			//rwd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    			//Javascript.set(rwd);
    			//return rwd;
    			 appdr = new AndroidDriver(new URL(appium_host_url.get()), capabilities); //Working code
    			
    			 //appdr.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    			 appdr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    			
    			Javascript.set(appdr);
    			
    			return (appdr);
        	}
        	else
        	{
        		System.out.println("isgrid parameter value is not supported. Shoud be yes/no");
        		return null;
        	}
        	
        	/*	
        	if (brows.get().equalsIgnoreCase("chrome") && grid.get().equalsIgnoreCase("no"))
    		{
        		ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/"); 
        		
        		System.setProperty("webdriver.chrome.driver",ProjectHomeDir + "/src/test/resources/chromedriver.exe");
    			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    			System.out.println("creating chromedriver instance");
    			RemoteWebDriver rwd = new ChromeDriver();
    			Javascript.set(rwd);
    			
    			return (rwd);
    						
    		}
    		else if((brows.get().equalsIgnoreCase("ff") ||brows.get().equalsIgnoreCase("firefox")) && (grid.get().equalsIgnoreCase("no")))
    		{
    			ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/");
    			System.setProperty("webdriver.gecko.driver",ProjectHomeDir + "/src/test/resources/geckodriver.exe");
    			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    			System.out.println("creating ffdriver instance");
    			RemoteWebDriver rwd = new FirefoxDriver();
    			Javascript.set(rwd);
    			
    			return (rwd);
    		   
    		}
    		else if(brows.get().equalsIgnoreCase("Edge") && grid.get().equalsIgnoreCase("no"))
    		{
    			ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/");
    			System.setProperty("webdriver.edge.driver",ProjectHomeDir + "/src/test/resources/MicrosoftWebDriver.exe");
    			DesiredCapabilities capabilities = DesiredCapabilities.edge();
    			
    			RemoteWebDriver rwd = new EdgeDriver();
    			Javascript.set(rwd);
    			
    			return (rwd);
    		}
    		else if(brows.get().equalsIgnoreCase("ie") && grid.get().equalsIgnoreCase("no"))
    		{
    			ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/");
    			System.setProperty("webdriver.ie.driver", ProjectHomeDir + "/src/test/resources/IEDriverServer.exe");
    			//DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    			System.out.println("creating iedriver instance");
    			RemoteWebDriver rwd = new InternetExplorerDriver();
    			Javascript.set(rwd);
    			
    			return (rwd);
    		}
        	
        	/////////////************************************* Grid ***********************************8
        	//Grid conditions below
        	if (brows.get().equalsIgnoreCase("chrome") && grid.get().equalsIgnoreCase("yes"))
    		{
        		
        		//ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/"); 
        		//System.setProperty("webdriver.chrome.driver",ProjectHomeDir + "/src/test/resources/chromedriver.exe");
        		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        		capabilities.setCapability("chrome.binary", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        		capabilities.setBrowserName("chrome");
    			capabilities.setPlatform(Platform.WINDOWS);    			    			
        		
        		RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        		
    			Javascript.set(rwd);
    			
    			return (rwd);
    						
    		}
    		else if((brows.get().equalsIgnoreCase("ff") ||brows.get().equalsIgnoreCase("firefox")) && (grid.get().equalsIgnoreCase("yes")))
    		{
    			//ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/"); 
        		
        		//System.setProperty("webdriver.gecko.driver",ProjectHomeDir + "/src/test/resources/geckodriver.exe");
    			//FirefoxOptions ffoptions = new FirefoxOptions();
    			//ffoptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
    			
    			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    			capabilities.setCapability("marionette", true);
    			capabilities.setBrowserName("firefox");
    			capabilities.setPlatform(Platform.WINDOWS);
    			
    			//capabilities.setCapability("moz:FirefoxOptions", ffoptions);
    			//capabilities.setBrowserName("firefox");
    			//capabilities.setCapability("marionette", false);
    			//capabilities.setPlatform(Platform.WIN10);    			    			
    			RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    			Javascript.set(rwd);
    			
    			return (rwd);
    		   
    		}
    		else if(brows.get().equalsIgnoreCase("Edge") && grid.get().equalsIgnoreCase("yes"))
    		{
    			//ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/"); 
    			//System.setProperty("webdriver.edge.driver",ProjectHomeDir + "/src/test/resources/MicrosoftWebDriver.exe");
    			
        		
    			DesiredCapabilities capabilities = DesiredCapabilities.edge();
    			capabilities.setPlatform(Platform.WINDOWS);    			    			
    			RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    			Javascript.set(rwd);
    			
    			return (rwd);
    		}
        	
    		else if(brows.get().equalsIgnoreCase("ie") && grid.get().equalsIgnoreCase("yes"))
    		{
    			//ProjectHomeDir = System.getProperty("user.dir").replace("\\", "/"); 
        		
    			//System.setProperty("webdriver.ie.driver", ProjectHomeDir + "/src/test/resources/IEDriverServer.exe");
    			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    			capabilities.setPlatform(Platform.WINDOWS);    			    
    			//capabilities.setBrowserName("iexplore");
    			//capabilities.setVersion("11");
    			RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    			rwd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    			rwd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    			Javascript.set(rwd);
    			
    			return (rwd);
    		}
    		else if(brows.get().equalsIgnoreCase("Android") && grid.get().equalsIgnoreCase("yes"))
    		{
    			
    			DesiredCapabilities capabilities = DesiredCapabilities.android();
    			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
    			
    			///ChromeOptions options=new ChromeOptions();
    		    //options.setExperimentalOption("androidPackage", "com.android.chrome");
    		    //capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    		    
    			//capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
    			capabilities.setCapability("appPackage", "com.android.chrome");
    			capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
    			
    			//capabilities.setCapability("appPackage", "com.android.chrome");
    		    //capabilities.setCapability("appActivity","com.google.android.apps.chrome.ChromeTabbedActivity");
    		    
    			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
    			
    			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Z5FUG6GINRV4R8FM"); //
    			capabilities.setCapability(MobileCapabilityType.UDID, "Z5FUG6GINRV4R8FM");
    			capabilities.setCapability(MobileCapabilityType.VERSION, "4.4.2");
    			//capabilities.setCapability("newCommandTimeout", 600);
    			  			
    			//capabilities.setPlatform(Platform.WINDOWS);    			    
    			//capabilities.setBrowserName("iexplore");
    			//capabilities.setVersion("11");
    			//RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); //Working code
    			//RemoteWebDriver rwd = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); //Working code
    			//manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    			//rwd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    			//Javascript.set(rwd);
    			//return rwd;
    			 appdr = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); //Working code
    			
    			 //appdr.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    			 appdr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    			
    			Javascript.set(appdr);
    			
    			return (appdr);
    		}
    		else if(brows.get().equalsIgnoreCase("Android1") && grid.get().equalsIgnoreCase("yes"))
    		{
    			
    			DesiredCapabilities capabilities = DesiredCapabilities.android();
    			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
    			
    			///ChromeOptions options=new ChromeOptions();
    		    //options.setExperimentalOption("androidPackage", "com.android.chrome");
    		    //capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    		    
    			//capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
    			capabilities.setCapability("appPackage", "com.android.chrome");
    			capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
    			
    			//capabilities.setCapability("appPackage", "com.android.chrome");
    		    //capabilities.setCapability("appActivity","com.google.android.apps.chrome.ChromeTabbedActivity");
    		    
    			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
    			//capabilities.setCapability("newCommandTimeout", 600);
    			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "4f9d96c0"); //
    			capabilities.setCapability(MobileCapabilityType.UDID, "4f9d96c0");
    			capabilities.setCapability(MobileCapabilityType.VERSION, "6.0.1");
    			
    			  			
    			//capabilities.setPlatform(Platform.WINDOWS);    			    
    			//capabilities.setBrowserName("iexplore");
    			//capabilities.setVersion("11");
    			//RemoteWebDriver rwd = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); //Working code
    			//RemoteWebDriver rwd = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); //Working code
    			  			
    			 appdr = new AndroidDriver(new URL("http://127.0.0.2:4730/wd/hub"), capabilities); //Working code
    			
    			 appdr.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    			 appdr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    			
    			Javascript.set(appdr);
    			
    			return (appdr);
    		}
        	
        	*/
        	return null;
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        		return null;
        	}
        	    		
        }
	};
	
	
	/**
	 * @author Sourav Mukherjee
	 * @Param URL
	 * @return void
	 * @throws Exception
	 * @Description Opens page in already opened browser as per supplied URL
	 * @Date Aug 7, 2014
	 */
	public static void OpenSFDCRecordAsperURL(String URL) throws Exception
	{
		WaitForPageToLoad(15);
		try 
		{
			myWD.get().get(URL);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String getParallelID() throws Exception
	{
		
		try 
		{
			return ("_"+ platform.get() + "_" + brows.get());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	
	/**
	 * @param ApplicationName
	 * @throws Exception
	 */
	public static void SelectApplication(String ApplicationName) throws Exception
	{
		try 
		{
		if (myWD.get().findElement(By.xpath("//*[@id='tsidLabel']")).getText().trim().equals(ApplicationName.trim()))
		{
			
			System.out.println("The application ("+ApplicationName+") is selected successfully.");
		}
		else
		{
			myWD.get().findElement(By.xpath("//*[@id='tsid-arrow']")).click();
			Thread.sleep(1000L);
        	if (myWD.get().findElement(By.xpath("//*[contains(@class,'menuButtonMenuLink') and contains(text(), '"+ApplicationName+"')]")).isDisplayed())
        	{
        		myWD.get().findElement(By.xpath(".//*[contains(@class,'menuButtonMenuLink') and contains(text(), '"+ApplicationName+"')]")).click();
        		
    			System.out.println("The application ("+ApplicationName+") is selected successfully.");
    	   	}
        	else
        	{
        		
				System.out.println("Unable to select the application for ("+ApplicationName+"). Please check the related xpaths or application name properly.");
        	}
		
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public synchronized boolean OpenURL(String[] args,String URL, String isgrid, String browser, String platform_os, String udid_m, String devname_m, String ver_m,String appium_url) throws Exception
	{
		try 
		{
			if ((args != null))
			{
				System.out.println("args[0] is: "+args[0]);
				//browser = args[0];
				
			}
			
			//*************************
			
			
			
			//***********************
			
			brows.set(browser);
			grid.set(isgrid);
			platform.set(platform_os);
			udid.set(udid_m);
			devicename.set(devname_m);
			version.set(ver_m);
			appium_host_url.set(appium_url);
			
			
			myWD.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			myWD.get().navigate().to(URL);
			//myWD.get().manage().window().maximize();
			if(myWD.get().toString().contains("ANDROID"))
			{
				System.out.println("Do nothing for now");
			}
				
			return true;
		
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static void changeDriverContextToNative() {
	    Set<String> contextNames = appdr.getContextHandles();
	    for (String contextName : contextNames) {
	        if (contextName.contains("NATIVE"))
	            appdr.context(contextName);
	    }
	}
	public static void changeDriverContextToWeb() {
	    Set<String> allContext = appdr.getContextHandles();
	    for (String context : allContext) {
	        if (context.contains("WEBVIEW"))
	        	appdr.context(context);
	    }
	}


	
	public static boolean LoginToSFDC(String un,String pw) throws Exception
	{
		try
		{
			
		//Javascript = (JavascriptExecutor) myWD.get();	
			
		System.out.println("javascript:"+Javascript.toString());	
		System.out.println("checking appiumdriver:"+myWD.get().toString());
			
		Thread.sleep(3000L);
		String xpath_UserName = "//input[normalize-space(@type)='email' and normalize-space(@id)='username'][1]";
		String xpath_Password = "//input[normalize-space(@type)='password' and normalize-space(@id)='password'][1]";
		String xpath_LoginButton = "//input[contains(@class,'button') and @type='submit'][1]";
		System.out.println("myWD.get().findElement(By.xpath(xpath_UserName)):"+myWD.get().findElement(By.xpath(xpath_UserName)));
		//myWD.get().findElement(By.xpath(xpath_UserName)).clear();
		//myWD.get().findElement(By.xpath(xpath_UserName)).sendKeys(un);
		myWD.get().findElement(By.xpath(xpath_UserName)).clear();
		myWD.get().findElement(By.xpath(xpath_UserName)).sendKeys(un);
		
	 	myWD.get().findElement(By.xpath(xpath_Password)).clear();
		myWD.get().findElement(By.xpath(xpath_Password)).sendKeys(pw);
		
		myWD.get().findElement(By.xpath(xpath_LoginButton)).click();
			
		System.out.println("(myWD.toString():"+myWD.get().toString());
		
		Thread.sleep(5000L);		
			
		System.out.println("count_driverinstance_chrome: "+flag_driverinstance_chrome);
		System.out.println("count_driverinstance_ff: "+flag_driverinstance_ff);
		//System.out.println("count_driverinstance_ie: "+count_driverinstance_ie);
		System.out.println("thrd_id.get():------------------->"+thrd_id.get());
		if(!(myWD.get().toString().contains("InternetExplorer") || myWD.get().toString().contains("ANDROID")))
		{
			if(
				( 
						(myWD.get().toString().contains("FirefoxDriver") &&  flag_driverinstance_ff == false) 
											||
						(myWD.get().toString().contains("ChromeDriver") &&  flag_driverinstance_chrome == false)
											||
						(myWD.get().toString().contains("RemoteWebDriver") &&  (myWD.get().toString().contains("chrome")) && flag_driverinstance_chrome == false)
											||
						(myWD.get().toString().contains("RemoteWebDriver") &&  (myWD.get().toString().contains("firefox")) && flag_driverinstance_chrome == false)
				) 
				
				&&
				
				(
						thrd_id.get() == null
				)
			   )							
				
			{
			
			thrd_id.set((int)Thread.currentThread().getId());
			
			WaitForElement("//a[text()='No Thanks'][1]", 60);
			myWD.get().findElement(By.xpath("//a[text()='No Thanks'][1]")).click();
			Thread.sleep(3000L);
			myWD.get().findElement(By.xpath("//label[@for='lex-checkbox-7'][1]/span[1]")).click();
			Thread.sleep(2000L);
			myWD.get().findElement(By.xpath("//input[@title='Send to Salesforce'][1]")).click();
			}
			if(myWD.get().toString().contains("ChromeDriver"))
			{
				flag_driverinstance_chrome = true;
			}
			if(myWD.get().toString().contains("FirefoxDriver"))
			{
				flag_driverinstance_ff = true;
			}
			
			
		}
		Thread.sleep(2000L);
		WebDriverWaitForElement("//a[contains(normalize-space(text()),'Home')]", 60);
		return true;
		}catch(Exception e)
		{        	
			e.printStackTrace();
			return false;
		}
		
}

	
	
	/**
	 * @author Sourav Mukherjee
	 * @Param 
	 * @return On Success it returns the entire URL of the WebDriver browser window, on Failure it returns blank value
	 * @throws Exception
	 * @Description Copies the Current URL of the WebDriver browser window, on Failure it returns blank value
	 * @Date Aug 7, 2014
	 */
	public static String GetCurrentURL() throws Exception
	{
		WaitForPageToLoad(30);
		try 
		{
			
			return myWD.get().getCurrentUrl();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * @param element
	 * @Description Scroll vertically/ horizontally to make the element visible on the screen. 
	 * @throws Exception
	 */
	public static void ScrollToElement(WebElement element) throws Exception
	{
		
		try
		{
			Javascript.get().executeScript("arguments[0].scrollIntoView();", element);
			
			//Coordinates coordinate = ((Locatable) element).getCoordinates();
			//Point cord = element.getLocation();
			//cord.getY();
			//- 300;
			//Locatable
						
			//Point coordinates = element.getLocation();
			
			//Robot robot = new Robot();
			//robot.mouseWheel(1);
			
			
			
			//coordinate.onPage();
			//coordinate.onScreen();
			//coordinate.inViewPort();
					
			System.out.println("Successfully scrolled untill element.");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Unable to scroll until elemnt");
		}
	}
public static ThreadLocal<RemoteWebDriver> GetDriver() throws Exception
{
	try {
		
		return myWD; 
				
	}catch(Exception e)
	{
		e.printStackTrace();
		return null;
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
public static WebElement WebDriverWaitForElement(WebElement elmnt, long waitingTimeinsec) throws Exception
{
	
    try {
    	 	WebDriverWait wait = new WebDriverWait(myWD.get(), waitingTimeinsec);
    	 	
    	 	return wait.until(ExpectedConditions.elementToBeClickable(elmnt));
       	 }
     	catch(Exception e)
     	{
     		e.printStackTrace();
     		return null;
    	 
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
	
	WaitForElement("//a[contains(@href,'logout.jsp')][1]", 10);
	
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
 * @throws Exception
 */
public static void Close() throws Exception
{
	try 
	{
	WaitForPageToLoad(10);
	myWD.get().close();
	
	System.out.println("Closed focused browser");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Unable to close driver instance.");
		
		
	}
	
}

public static void Quit() throws Exception
{
	try 
	{
		_killProcess("chromedriver.exe");
		_killProcess("chrome.exe");
		
		_killProcess("geckodriver.exe");
		_killProcess("firefox.exe");
		
		_killProcess("IEDriverServer.exe");
		_killProcess("iexplore.exe");
	
	
	System.out.println("Closed webdriver instance");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("Unable to close driver instance.");
		
		
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
public static synchronized boolean WaitForElement(String xpath, long waitingTimeinsec) throws Exception
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
	/*
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
	*/
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
    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmSSS");
    return (sdf.format(cal.getTime()));                     
          
}

//******************** Part of OR Library **********************
public static MemberOfLink Link(String LinkName) throws Exception
{
	return new MemberOfLink(LinkName);
}
public static MemberOfButton Button(String ButtonName) throws Exception
{
	return new MemberOfButton(ButtonName);
}
public static MemberOfField Field(String FieldName) throws Exception
{
	return new MemberOfField(FieldName);
}


/**
 * @author Sourav Mukherjee
 * @Param serviceName
 * @return void
 * @throws Exception
 * @Description Kills the running process 
 * @Date Aug 7, 2014
 */
private static final String TASKLIST = "tasklist";
private static final String KILL = "taskkill /F /IM ";

public boolean _isProcessRunning(String serviceName) throws Exception {

 Process p = Runtime.getRuntime().exec(TASKLIST);
 BufferedReader reader = new BufferedReader(new InputStreamReader(
   p.getInputStream()));
 String line;
 while ((line = reader.readLine()) != null) {

  System.out.println(line);
  if (line.contains(serviceName)) {
   return true;
  }
 }

 return false;

}

public static void _killProcess(String serviceName) throws Exception {

  Runtime.getRuntime().exec(KILL + serviceName);

 }

/**
* @author Cognizant
 * @param Year
 * @param Month
 * @param Date
 * @return true of the date is selected successfully else return false
 * @Description You should click on the date lookup field to enable the OOB date lookup, then only you can call this function to select any specific date by year,month and date
 * @throws Exception
 */
public static synchronized boolean SelectFromDateLookup(String Year,String Month,String Date) throws Exception
{
	try
	{
		//myWD.findElement(By.xpath("//div[normalize-space(@class)='datePicker' and contains(normalize-space(@style),'block')][1]/descendant-or-self::td[contains(normalize-space(@class),'selectedDate')][1]")).click();
		
		//Selecting the Year as supplied
		Year = Year.replace(".0", "").replace(".00", "");
		Date = Date.replace(".0", "").replace(".00", "");
		ScrollToElement(myWD.get().findElement(By.xpath("//div[normalize-space(@class)='datePicker' and contains(normalize-space(@style),'block')][1]/descendant-or-self::select[@title='Year'][1]")));
		WaitForElement("//div[normalize-space(@class)='datePicker' and contains(normalize-space(@style),'block')][1]/descendant-or-self::select[@title='Year'][1]", 10);
		WebElement getsingleWebelement = myWD.get().findElement(By.xpath("//div[normalize-space(@class)='datePicker' and contains(normalize-space(@style),'block')][1]/descendant-or-self::select[@title='Year'][1]"));
		Select s = new Select(getsingleWebelement);
		s.selectByVisibleText(Year);
		
		System.out.println("Month is:"+Month);
		//Selecting the Month as supplied
		getsingleWebelement = myWD.get().findElement(By.xpath("//div[normalize-space(@class)='datePicker' and contains(normalize-space(@style),'block')][1]/descendant-or-self::select[@title='Month'][1]"));
		s = new Select(getsingleWebelement);
		s.selectByVisibleText(Month);
		
		
		//Selecting the Date as supplied
		if(Date.substring(0, 1).equals("0"))
		{
			Date = Date.replace("0", "");
		}
		myWD.get().findElement(By.xpath("//div[normalize-space(@class)='datePicker' and contains(normalize-space(@style),'block')][1]/descendant-or-self::table[normalize-space(@class)='calDays'][1]/descendant-or-self::td[normalize-space(text())='"+Date+"'][1]")).click();
		
		
		System.out.println("Supplied date value ("+Date+" "+Month+" "+Year+") is selected from date lookup.");
		AddToXLLogs("Supplied date value ("+Date+" "+Month+" "+Year+") is selected from date lookup.", "Pass");
		return true;
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println();
		AddToXLLogs("Unable to select the supplied date from SFDC Date lookup.", "Fail");
		return false;
	}
	
}

/**

 */
/**
 * @author Cognizant
 * @Description This function selects a date from Salesforce date lookup as per supplied parameter.
 * @param nextdaycount starts from 0,1,2 i.e. today,tomorrow,day after tomorrow so on.
 * @return
 * @throws Exception
 */
public static synchronized boolean SelectFromDateLookup(int nextdaycount) throws Exception
{
	try
	{
		//selecting today's date
		if(nextdaycount == 0)
		{
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
			String D = sdf.format(cal.getTime()).toString().substring(0, sdf.format(cal.getTime()).toString().indexOf(" ")).trim();
			List<String> DMY = new ArrayList();
			for (String val: sdf.format(cal.getTime()).toString().split(" ", 3))
			{
				DMY.add(val);
		         
		    }
			for(int i=0;i<DMY.size();i++)
			{
				System.out.println(DMY.get(i));
			}
			
			sfdc.SelectFromDateLookup(DMY.get(2),DMY.get(1),DMY.get(0));
							
			
		}
		//Selecting today + nextdaycount 
		if (nextdaycount > 0)
		{
			String DAY="",mon="";
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			String D = sdf.format(cal.getTime()).toString().substring(0, sdf.format(cal.getTime()).toString().indexOf(" ")).trim();
			List<String> DMY = new ArrayList();
			for (String val: sdf.format(cal.getTime()).toString().split(" ", 3))
			{
				DMY.add(val);
		         
		    }
			String month = DMY.get(1);
			int totaldaycount = Integer.valueOf(DMY.get(0)).intValue() + nextdaycount;
			System.out.println("totaldaycount");
			//Calculating 31 day month and the related day
			if (DMY.get(1).contains("Jan") ||DMY.get(1).contains("Mar")||DMY.get(1).contains("May")||DMY.get(1).contains("Jul")||DMY.get(1).contains("Aug")||DMY.get(1).contains("Oct")||DMY.get(1).contains("Dec"))
			{
				if(totaldaycount>31)
				{
					Calendar currentMonth = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
					currentMonth.add(Calendar.MONTH, 1);
					mon = dateFormat.format(currentMonth.getTime());
					int day = totaldaycount - 31;
					DAY = Integer.toString(day);
					
				}
				else
				{
					Calendar currentMonth = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
					mon = dateFormat.format(currentMonth.getTime());
					int day = totaldaycount;
					DAY = Integer.toString(day);
					
				}
			}
			//Calculating 30 day month and the related day
			if (DMY.get(1).contains("Apr") ||DMY.get(1).contains("Jun")||DMY.get(1).contains("Sep")||DMY.get(1).contains("Nov")) 
			{
				if(totaldaycount>30) 
				{
					Calendar currentMonth = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
					currentMonth.add(Calendar.MONTH, 1);
					mon = dateFormat.format(currentMonth.getTime());
					int day = totaldaycount - 30;
					DAY = Integer.toString(day);
					
				}
				else
				{
					Calendar currentMonth = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
					//currentMonth.add(Calendar.MONTH, 1);
					mon = dateFormat.format(currentMonth.getTime());
					int day = totaldaycount;
					DAY = Integer.toString(day);
				}
			}
			//Calculating 28 day month and the related day
			if (DMY.get(1).contains("Feb")  && (totaldaycount>28) ) 
			{
				Calendar currentMonth = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
				currentMonth.add(Calendar.MONTH, 1);
		        mon = dateFormat.format(currentMonth.getTime());
		        int day = totaldaycount - 28;
		        DAY = Integer.toString(day);
		        
			}
			//Calculating 28 day month and the related day
			if (DMY.get(1).contains("Feb")  && (totaldaycount<28) ) 
			{
				Calendar currentMonth = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");
				currentMonth.add(Calendar.MONTH, 1);
		        mon = dateFormat.format(currentMonth.getTime());
		        int day = totaldaycount;
		        DAY = Integer.toString(day);
		        
			}
			System.out.println("Date Value Selected was: "+DMY.get(2)+" "+mon+" "+DAY);
			sfdc.SelectFromDateLookup(DMY.get(2),mon,DAY);
		}
		return true;
		
	}
	catch(Exception e)
	{
		
		return false;
	}
}




}
