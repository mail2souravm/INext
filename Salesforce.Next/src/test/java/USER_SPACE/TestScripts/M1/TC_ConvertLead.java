package USER_SPACE.TestScripts.M1;


import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import SOURCE_CODE.DB;
import SOURCE_CODE.SFDCAutomationFW;
import USER_SPACE.ObjectRepository.AccountScreen;
import USER_SPACE.ObjectRepository.LeadScreen;
import USER_SPACE.Prerequisite.DataSetup;



public class TC_ConvertLead {

	public static final ThreadLocal<String> brow = new ThreadLocal<String>();
	public static final ThreadLocal<String> grid = new ThreadLocal<String>();
	public static final ThreadLocal<String> platform = new ThreadLocal<String>();
	public static final ThreadLocal<String> udid = new ThreadLocal<String>();
	public static final ThreadLocal<String> devicename = new ThreadLocal<String>();
	public static final ThreadLocal<String> version = new ThreadLocal<String>();
	public static final ThreadLocal<String> appium_url = new ThreadLocal<String>();
	
	
	public static final ThreadLocal<SFDCAutomationFW> sfdc = new ThreadLocal<SFDCAutomationFW>(){@Override protected SFDCAutomationFW initialValue(){return (new SFDCAutomationFW());}};
	@Parameters({"isgrid","browser","platform","udid","devicename","version","appium_host_url"})
	@Test
	public void MyTestScenario(String isgrid, String browser, String platform, String udid, String devicename,String version, String appium_host_url) throws Exception 
	{
				
		try {
			
			
		this.grid.set(isgrid);
		this.brow.set(browser);
		this.platform.set(platform);
		this.udid.set(udid);
		this.devicename.set(devicename);
		this.version.set(version);
		this.appium_url.set(appium_host_url);
		
		//***************** Test Case Body ***********************
		
		long threadId = Thread.currentThread().getId();
		System.out.println("Begin: This task is performed by:"+threadId);
		Thread.sleep(2000L);
		System.out.println("from TC_CreateLead --> Browser:"+brow.get());
		sfdc.get().OpenURL(null, "https://login.salesforce.com",grid.get(),brow.get(),this.platform.get(), this.udid.get(),this.devicename.get(),this.version.get(),this.appium_url.get());
		sfdc.get().LoginToSFDC("mail2souravm@gmail.com","Welcome400");
		sfdc.get().SelectApplication("Sales");
		Thread.sleep(4000L);
		DB.Connect(DataSetup.TC_CreateLead);
		sfdc.get().OpenSFDCRecordAsperURL(DB.ReadXLData("TC_CreateLead", "SFDCURL", "SN", "SN01"+SFDCAutomationFW.getParallelID()));
		LeadScreen.ConvertButton().Click();
		
		LeadScreen.ConvertButton().Click();
		
		Thread.sleep(3000L);
		String AccountName = AccountScreen.AccountNameField().GetViewOnlyValue();
		DB.Connect(DataSetup.TC_CreateLead);
		DB.UpdateXLCell("TC_CreateLead", AccountName, "AccountName", "SN", "SN01"+SFDCAutomationFW.getParallelID());
		
		//sfdc.get().LogOff();
		threadId = Thread.currentThread().getId();
		System.out.println("End: This task is performed by:"+threadId);
			
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
}
